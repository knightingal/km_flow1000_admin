package org.nanking.km_flow1000_admin

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
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
    ) {
        val lazyStaggeredGridState = rememberLazyStaggeredGridState()
        LazyVerticalStaggeredGrid(
            state = lazyStaggeredGridState,
            columns = StaggeredGridCells.Fixed(3),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxSize(),
        ) {
            items(albumConfigList.size) { index ->
                Box(
                    modifier = Modifier.aspectRatio(
                        ratio = albumConfigList[index].coverSection.coverWidth.toFloat()
                                / albumConfigList[index].coverSection.coverHeight.toFloat()
                    )
                ) {
                    val coverUrl = albumConfigList[index].coverUrl()
                    logger.i { "Display Cover URL: $coverUrl" }
//                    AsyncImage(model = coverUrl, contentDescription = null)
                    AlbumCoverCard(albumConfig = albumConfigList[index])
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
fun AlbumCoverCard(modifier: Modifier = Modifier, albumConfig: AlbumConfig) {
    Card(
        border = BorderStroke(2.dp, Color.Blue), colors = CardColors(
            contentColor = Color.Red, containerColor = Color.Black,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.LightGray,
        ), modifier = modifier
    ) {
        Column() {
            AsyncImage(model = albumConfig.coverUrl(), contentDescription = null)
            Text(albumConfig.name)

        }
    }
}

@Composable
@Preview
fun AlbumCoverCardPreview() {
    AlbumCoverCard(
        modifier = Modifier,
        albumConfig = AlbumConfig(
            0, "1803", false, null, "1803", null,
            Flow1000Section(
                0,
                "",
                "diranme",
                "",
                "coverfilename",
                "",
                4220,
                2815,
                listOf()
            )
        )
    )
}