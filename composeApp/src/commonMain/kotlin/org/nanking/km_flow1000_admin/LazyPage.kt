package org.nanking.km_flow1000_admin

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyLayoutScrollScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.Companion
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch

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




@Composable
@Preview
fun LazyStaggeredGridCustomScrollUsingLazyLayoutScrollScopeSample() {
    suspend fun LazyStaggeredGridState.customScroll(
        block: suspend LazyLayoutScrollScope.() -> Unit
    ) = scroll { block.invoke(LazyLayoutScrollScope(this@customScroll, this)) }

    val itemsList = (0..100).toList()
    val state = rememberLazyStaggeredGridState()
    val scope = rememberCoroutineScope()

    Column(Modifier.verticalScroll(rememberScrollState())) {
        Row {
            Button(
                modifier = Modifier.padding(2.dp),
                onClick = {
                    scope.launch {
                        state.customScroll {
                            snapToItem(40, 20) // teleport to item 40
                            val distance = calculateDistanceTo(50).toFloat()
                            var previousValue = 0f
                            animate(
                                0f,
                                distance,
                                animationSpec = tween(5_000),
                            ) { currentValue, _ ->
                                previousValue += scrollBy(currentValue - previousValue)
                            }
                        }
                    }
                }
            ) {
                Text("Scroll To Item 50")
            }
            Button(
                modifier = Modifier.padding(2.dp),
                onClick = {
                    scope.launch {
                        state.customScroll {
                            snapToItem(0, 0) // teleport to item 40
                        }
                    }
                }
            ) {
                Text("Scroll To Item 0")
            }
            Button(
                modifier = Modifier.padding(2.dp),
                onClick = {
                    scope.launch {
                        state.customScroll {
                            scrollBy((45 + 4).toFloat())
                        }
                    }
                }
            ) {
                Text("Scroll +50 offset")
            }
        }
        LazyHorizontalStaggeredGrid(
            state = state,
            rows = StaggeredGridCells.Fixed(3),
            modifier = Modifier.height(600.dp).fillMaxWidth(),
        ) {
            items(itemsList) {
                Box(Modifier.padding(2.dp).background(Color.Red).size(45.dp)) {
                    Text(it.toString())
                }
            }
        }
    }
}