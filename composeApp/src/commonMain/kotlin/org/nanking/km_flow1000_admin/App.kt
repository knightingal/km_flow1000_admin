package org.nanking.km_flow1000_admin

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        Scaffold { it ->
            it.hashCode()
            NavHost(
                navController, startDestination = "lazyPage",
                enterTransition = { slideInHorizontally() },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            ) {
                composable("home") { Home(navController) }
                composable("lazyPage") { LazyPage(navController) }
                composable("greeting") { GreetingApp() }
            }
        }
    }

}