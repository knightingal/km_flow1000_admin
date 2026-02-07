package org.nanking.km_flow1000_admin

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import km_flow1000_admin.composeapp.generated.resources.Res
import km_flow1000_admin.composeapp.generated.resources.ab1_inversions
import km_flow1000_admin.composeapp.generated.resources.fc1_short_mantras

@Composable
@Preview
fun App() {
    MaterialTheme {
//        val navController = rememberNavController()
//        Scaffold {
//            NavHost(navController, startDestination = "home",
//                enterTransition = { slideInHorizontally() },
//                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
//            ) {
//                composable("home") {Home(navController)}
//                composable("innerPage") {InnerPage(navController)}
//            }
//        }
        LazyPage()
    }

}