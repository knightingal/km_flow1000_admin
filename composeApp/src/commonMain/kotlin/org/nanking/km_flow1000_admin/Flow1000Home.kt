package org.nanking.km_flow1000_admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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

@Composable
fun Flow1000Home(navController: NavHostController) {

    val logger = getLogger("Flow1000Home")
    val rocketComponent = RocketComponent()
    var albumConfigList by remember { mutableStateOf(listOf<AlbumConfig>()) }
    LaunchedEffect(true) {
        albumConfigList = rocketComponent.fetchAlbumConfigList()
    }
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .systemBarsPadding()
            .fillMaxSize()
            .padding(4.dp)
    ) {
        val lazyStaggeredGridState = rememberLazyStaggeredGridState()
        LazyVerticalStaggeredGrid(
            state = lazyStaggeredGridState,
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(albumConfigList.size) { index ->
                val coverUrl = albumConfigList[index].cover
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

@Composable
fun Flow1000AlbumPage(navController: NavHostController, albumConfig: AlbumParam) {
    val logger = getLogger("Flow1000AlbumPage")
    val rocketComponent = RocketComponent()
    var pinIndexList by remember { mutableStateOf(listOf<PicIndexItem>()) }
    LaunchedEffect(true) {
        pinIndexList = rocketComponent.fetchPicIndex()
    }
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .systemBarsPadding()
            .fillMaxSize()
            .padding(4.dp)
    ) {
        val lazyStaggeredGridState = rememberLazyStaggeredGridState()
        LazyVerticalStaggeredGrid(
            state = lazyStaggeredGridState,
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(pinIndexList.size) { index ->
                val coverUrl = pinIndexList[index].cover
                logger.i { "Display Cover URL: $coverUrl" }
                AlbumCoverCard(albumConfig = pinIndexList[index]) {
                }
            }
        }
        PlatformVerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            lazyStaggeredGridState = lazyStaggeredGridState
        )
    }

}

@Composable
fun AlbumCoverCard(
    modifier: Modifier = Modifier,
    albumConfig: AlbumConfigCover<*>,
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
            if (albumConfig.cover is String) {
                AsyncImage(model = albumConfig.cover, contentDescription = null)
            } else if (albumConfig.cover is DrawableResource) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(albumConfig.cover as DrawableResource),
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
        modifier = Modifier,
        albumConfig = object : AlbumConfigCover<DrawableResource> {
            override val width: Int
                get() = 640
            override val height: Int
                get() = 426
            override val cover: DrawableResource
                get() = Res.drawable.fc6_nightly_wind_down
            override val name: String
                get() = "AI20220605211354"
        }
    )
}