package org.nanking.km_flow1000_admin

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage

val colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow)

fun colorByIndex(index: Int): Color {
    return colors[index % colors.size]
}
class LazyViewModel : ViewModel() {
}

@Composable
fun LazyPage(
    navController: NavHostController,
    viewModel: LazyViewModel = viewModel { LazyViewModel() }
) {
    val rocketComponent = RocketComponent()
    var picIndexList by remember { mutableStateOf(listOf<PicIndexItem>()) }

    LaunchedEffect(true) {
        picIndexList = rocketComponent.fetchPicIndex()
    }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .systemBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { navController.popBackStack() }) {
            Text("back!")
        }
        Box(modifier = Modifier.fillMaxSize()) {
            val state = rememberLazyStaggeredGridState()
            LazyVerticalStaggeredGrid(
                state = state,
                columns = StaggeredGridCells.Fixed(3),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(picIndexList.size) { index ->
                    Box(
                        Modifier
                            .aspectRatio(ratio = picIndexList[index].coverWidth.toFloat() / picIndexList[index].coverHeight.toFloat())
                    ) {
                        AsyncImage(
                            model = picIndexList[index].coverUrl(), contentDescription = null,
                        )
                    }
                }
            }
//            VerticalScrollbar(
//                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
//                adapter = rememberScrollbarAdapter(
//                    scrollState = state
//                )
//            )


        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyPagePreview() {
    LazyPage(navController = rememberNavController())
}
