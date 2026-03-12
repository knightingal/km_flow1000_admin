package org.nanking.km_flow1000_admin

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
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


suspend fun LazyStaggeredGridState.customScroll(
    block: suspend LazyLayoutScrollScope.() -> Unit
) {
    scroll { block.invoke(LazyLayoutScrollScope(this@customScroll, this)) }
}

@Composable
fun LazyPage(
    navController: NavHostController,
    pageId: String,
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
        Button(onClick = {
            navController.previousBackStackEntry?.savedStateHandle["showContent"] = "response content"
            navController.popBackStack()
        }) {
            Text("back!")
        }
        Box(modifier = Modifier.fillMaxSize()) {
            val lazyStaggeredGridState: LazyStaggeredGridState = rememberLazyStaggeredGridState()
            LazyVerticalStaggeredGrid(
                state = lazyStaggeredGridState,
                columns = StaggeredGridCells.Fixed(3),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(picIndexList.size) { index ->
                    SectionCoverCard(picIndexItem = picIndexList[index])
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
fun SectionCoverCard(modifier: Modifier = Modifier, picIndexItem: PicIndexItem) {
    Card(
        border = BorderStroke(2.dp, Color.Blue), colors = CardColors(
            contentColor = Color.Red, containerColor = Color.Black,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.LightGray,
        ), modifier = modifier
    ) {
        Column() {
            Box(
                modifier = Modifier.aspectRatio(
                    ratio = picIndexItem.coverWidth.toFloat()
                            / picIndexItem.coverHeight.toFloat()
                )
            ) {
                AsyncImage(model = picIndexItem.cover, contentDescription = null)
            }
            Text(picIndexItem.name)

        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyPagePreview() {
    LazyPage(navController = rememberNavController(), "0")
}




@Composable
@Preview
fun LazyStaggeredGridCustomScrollUsingLazyLayoutScrollScopeSample() {
    suspend fun LazyStaggeredGridState.customScroll(
        block: suspend LazyLayoutScrollScope.() -> Unit
    ) {
        val block1: suspend ScrollScope.() -> Unit = {
            LazyLayoutScrollScope(this@customScroll, this).block()
        }
        scroll(scrollPriority = MutatePriority.Default, block1)
    }

    val itemsList = (0..100).toList()
    val state = rememberLazyStaggeredGridState()
    val scope = rememberCoroutineScope()
    val currentDensity = LocalDensity.current

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
            Button(
                modifier = Modifier.padding(2.dp),
                onClick = {
                    scope.launch {
                        val nowOffset = state.scrollIndicatorState!!.scrollOffset
                        val targetOffset = with(currentDensity) {
                             50.dp.toPx() - nowOffset
                        }
                        state.customScroll {
                            scrollBy(targetOffset)
                        }
                    }
                }
            ) {
                Text("Scroll 50dp offset")
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

@Composable
fun LazyScrollable() {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color(180, 180, 180))
            .padding(10.dp)
    ) {

        val state = rememberLazyListState()

        LazyColumn(Modifier.fillMaxSize().padding(end = 12.dp), state) {
            items(1000) { x ->
                TextBox("Item #$x")
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

@Composable
fun TextBox(text: String = "Item") {
    Box(
        modifier = Modifier.height(32.dp)
            .fillMaxWidth()
            .background(color = Color(0, 0, 0, 20))
            .padding(start = 10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = text)
    }
}