package org.nanking.km_flow1000_admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import km_flow1000_admin.composeapp.generated.resources.Res
import km_flow1000_admin.composeapp.generated.resources.fc6_nightly_wind_down
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class Flow1000HomeViewModel : ViewModel() {
    private var _albumConfigList = MutableStateFlow(listOf<AlbumConfig>())
    val albumConfigList: StateFlow<List<AlbumConfig>> get() = _albumConfigList
    init {
        viewModelScope.launch {
            val fetchResult = RocketComponent().fetchAlbumConfigList()
            _albumConfigList.value = fetchResult
        }
    }
}


@Composable
fun Flow1000Home(navController: NavHostController, viewModel: Flow1000HomeViewModel) {

    val logger = getLogger("Flow1000Home")
    val albumConfigList by viewModel.albumConfigList.collectAsStateWithLifecycle()
    BoxWithConstraints(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .systemBarsPadding()
            .fillMaxSize()
            .padding(4.dp)
    ) {
        val columnCount = if (maxWidth < 960.dp) 2 else 4
        val lazyStaggeredGridState = rememberLazyStaggeredGridState()
        LazyVerticalStaggeredGrid(
            state = lazyStaggeredGridState,
            columns = StaggeredGridCells.Fixed(columnCount),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(albumConfigList.size) { index ->
                val coverUrl = albumConfigList[index].coverUri
                logger.i { "Display Cover URL: $coverUrl" }
                AlbumCoverCard(albumConfig = albumConfigList[index]) {
                    val albumConfig = albumConfigList[index]
                    navController.navigate(AlbumParam(albumConfig.name, albumConfig.sourcePath))
                }
            }
        }
        PlatformVerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            lazyStaggeredGridState = lazyStaggeredGridState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Flow1000SectionPage(navController: NavHostController, sectionParam: SectionParam) {
    val logger = getLogger("Flow1000SectionPage")
    val rocketComponent = RocketComponent()
    var sectionDetail by remember { mutableStateOf<SectionDetail?>(null) }
    LaunchedEffect(true) {
        sectionDetail = rocketComponent.fetchSectionContent(sectionParam.id)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(sectionDetail?.title ?: "", color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            )
        },
        floatingActionButton = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .systemBarsPadding()
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            val lazyStaggeredGridState = rememberLazyListState()
            LazyColumn(
                state = lazyStaggeredGridState,
                contentPadding = PaddingValues(top = 2.dp, bottom = 2.dp),
                modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp),
            ) {
                items(sectionDetail?.pics?.size ?: 0) { index ->
                    val pic = sectionDetail!!.pics[index]
                    pic.sectionDir = sectionDetail!!.dirName
                    pic.albumSourcePath = sectionParam.albumSourcePath
                    logger.i { "Display Pics: ${pic.coverUri}" }
                    FitSizeImageCard(albumConfig = pic)
                }
            }
            PlatformVerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                lazyStaggeredGridState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Flow1000AlbumPage(navController: NavHostController, albumConfig: AlbumParam) {
    val logger = getLogger("Flow1000AlbumPage")
    val rocketComponent = RocketComponent()
    var pinIndexList by remember { mutableStateOf(listOf<PicIndexItem>()) }
    LaunchedEffect(true) {
        pinIndexList = rocketComponent.fetchPicIndex(albumConfig.name)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(albumConfig.name)
                }
            )
        },
        floatingActionButton = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {
        BoxWithConstraints (
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .systemBarsPadding()
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            val columnCount = if (maxWidth < 960.dp) 2 else 4
            val lazyStaggeredGridState = rememberLazyStaggeredGridState()
            LazyVerticalStaggeredGrid(
                state = lazyStaggeredGridState,
                columns = StaggeredGridCells.Fixed(columnCount),
                contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp, vertical = 0.dp),
            ) {
                items(pinIndexList.size) { index ->
                    val picIndex = pinIndexList[index]
                    picIndex.albumSourcePath = albumConfig.albumSourcePath
                    logger.i { "Display Cover URL: ${picIndex.coverUri}" }
                    AlbumCoverCard(albumConfig = picIndex) {
                        navController.navigate(SectionParam(picIndex.name, picIndex.index, albumSourcePath = albumConfig.albumSourcePath))
                    }
                }
            }
            PlatformVerticalScrollbar(
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                lazyStaggeredGridState = lazyStaggeredGridState
            )
        }
    }
}

@Composable
fun FitSizeImageCard(
    albumConfig: CardCover<*>,
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(top = 2.dp, bottom = 2.dp), contentAlignment = Alignment.Center) {
        val maxWidth = constraints.maxWidth

        val targetWidth: Int = if (maxWidth > albumConfig.width) {
            albumConfig.width
        } else {
            maxWidth
        }

        Card(
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
            colors = CardColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.LightGray,
            ), modifier = Modifier.wrapContentSize(),
        ) {
            Box(
                modifier = Modifier.width(targetWidth.dp).aspectRatio(
                    ratio = albumConfig.width.toFloat()
                            / albumConfig.height.toFloat()
                )
            ) {
                if (albumConfig.coverUri is String) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = albumConfig.coverUri, contentDescription = null
                    )
                } else if (albumConfig.coverUri is DrawableResource) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(albumConfig.coverUri as DrawableResource),
                        contentDescription = null,
                    )
                }
            }
        }
    }
}


@Composable
fun AlbumCoverCard(
    albumConfig: CardCover<*>,
    onClick: () -> Unit = {}
) {
    Card(
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
        colors = CardColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.LightGray,
        ), modifier = Modifier.wrapContentSize(),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.aspectRatio(
                ratio = albumConfig.width.toFloat()
                        / albumConfig.height.toFloat()
            )
        ) {
            if (albumConfig.coverUri is String) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = albumConfig.coverUri, contentDescription = null
                )
            } else if (albumConfig.coverUri is DrawableResource) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(albumConfig.coverUri as DrawableResource),
                    contentDescription = null,
                )
            }
        }
        Text(albumConfig.name, modifier = Modifier.padding(16.dp))
    }
}

@Composable
@Preview
fun AlbumCoverCardPreview() {
    AlbumCoverCard(
        albumConfig = object : CardCover<DrawableResource> {
            override val width: Int
                get() = 640
            override val height: Int
                get() = 426
            override val coverUri: DrawableResource
                get() = Res.drawable.fc6_nightly_wind_down
            override val name: String
                get() = "AI20220605211354"
        }
    )
}

@Composable
@Preview(widthDp = 360)
fun FitSizeImageCardPreviewSmaller() {
    FitSizeImageCard(
        albumConfig = object : CardCover<DrawableResource> {
            override val width: Int
                get() = 640
            override val height: Int
                get() = 426
            override val coverUri: DrawableResource
                get() = Res.drawable.fc6_nightly_wind_down
            override val name: String
                get() = "AI20220605211354"
        }
    )
}

@Composable
@Preview(widthDp = 800, heightDp = 600)
fun FitSizeImageCardPreviewBigger() {
    FitSizeImageCard(
        albumConfig = object : CardCover<DrawableResource> {
            override val width: Int
                get() = 640
            override val height: Int
                get() = 426
            override val coverUri: DrawableResource
                get() = Res.drawable.fc6_nightly_wind_down
            override val name: String
                get() = "AI20220605211354"
        }
    )
}