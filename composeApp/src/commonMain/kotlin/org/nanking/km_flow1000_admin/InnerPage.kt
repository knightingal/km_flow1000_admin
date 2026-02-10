package org.nanking.km_flow1000_admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun InnerPage(navController: NavHostController) {
    Column (
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .systemBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
        Box(
            modifier = Modifier
                .height(16.dp)
                .width(320.dp)
                .background(color = Color.Red)
        )
        Box(
            modifier = Modifier
                .height(16.dp)
                .width(320.dp)
                .background(color = Color.Green)
        )
        Box(
            modifier = Modifier
                .height(16.dp)
                .width(320.dp)
                .background(color = Color.Blue)
        )
        Box(
            modifier = Modifier
                .height(16.dp)
                .width(320.dp)
                .background(color = Color.Yellow)
        )
        Box(
            modifier = Modifier
                .height(16.dp)
                .width(320.dp)
                .background(color = Color.Cyan)
        )
        Box(
            modifier = Modifier
                .height(16.dp)
                .width(320.dp)
                .background(color = Color.Magenta)
        )
        Text("Inner Page")
    }

}

@Preview
@Composable
fun InnerPreview() {
    InnerPage(rememberNavController())
}
