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
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class LazyParam(val pageId: String)

@Serializable
data class HomeParam(val pageId: String)

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        Scaffold { it ->
            it.hashCode()
            NavHost(
                navController, startDestination = HomeParam("0"),
                enterTransition = { slideInHorizontally() },
                exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
            ) {
//                composable("home") { Home(navController) }
                composable<HomeParam> {backStackEntry->
                    val homeParam = backStackEntry.toRoute<HomeParam>()
                    Home(navController, homeParam.pageId)
                }
                composable("flow1000Home") { Flow1000Home(navController) }
                composable<LazyParam> { backStackEntry->
                    val lazyParam = backStackEntry.toRoute<LazyParam>()
                    LazyPage(navController, lazyParam.pageId) }
                composable("greeting") { GreetingApp() }
                composable("lazySample") { LazyStaggeredGridCustomScrollUsingLazyLayoutScrollScopeSample() }
                composable("lazyScrollable") { LazyScrollable() }
            }
        }
    }

}