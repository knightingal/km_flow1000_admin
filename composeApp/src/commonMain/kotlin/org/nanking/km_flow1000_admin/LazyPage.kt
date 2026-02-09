package org.nanking.km_flow1000_admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow)

fun colorByIndex(index: Int): Color {
    return colors[index % colors.size]
}

@Composable
fun LazyPage(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { navController.popBackStack() }) {
            Text("back!")
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            content = {
                items(600) {
                    Box(Modifier
                        .aspectRatio(ratio = (1.0 + 0.2 * (it % 4).toFloat()).toFloat())
                        .background(colorByIndex(it))) {
                        Text("Item $it",
                            Modifier.align(Alignment.Center),
                            fontSize = TextUnit(25f, TextUnitType.Sp),
                            color = colorByIndex(it + 1)
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun LazyPagePreview() {
    LazyPage(navController = rememberNavController())
}
