package org.nanking.km_flow1000_admin

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.codelab.basiclayouts.ui.theme.Flow1000Theme
import kotlinx.serialization.Serializable

const val IMAGE_SERVER = "192.168.2.12:8082"

const val API_SERVER = "192.168.2.12:8000"


@Serializable
data class LazyParam(val pageId: String)

@Serializable
data class HomeParam(val pageId: String)

@Serializable
data class Flow1000AlbumPageParam(val name: String, val albumSourcePath: String)

@Serializable
data class Flow1000SectionPageParam(val name: String, val id: Long, val albumSourcePath: String)

@Composable
@Preview
fun App() {
    Flow1000Theme {
        SharedTransitionLayout {
            val navController = rememberNavController()
            Scaffold { it ->
                it.hashCode()
                NavHost(
                    navController, startDestination = "flow1000Home",
//                  enterTransition = { slideInHorizontally() },
//                  exitTransition = { slideOutHorizontally(targetOffsetX = { -it }) },
                ) {
                    composable<HomeParam> { backStackEntry ->
                        val homeParam = backStackEntry.toRoute<HomeParam>()
                        Home(navController, homeParam.pageId)
                    }
                    composable("flow1000Home") {
                        Flow1000Home(
                            navController,
                            sharedTransitionScope = this@SharedTransitionLayout,
                            animatedContentScope = this@composable,
                            viewModel = viewModel { Flow1000HomeViewModel() }
                        )
                    }
                    composable<Flow1000AlbumPageParam> { backStackEntry ->
                        val albumConfig = backStackEntry.toRoute<Flow1000AlbumPageParam>()
                        Flow1000AlbumPage(
                            navController,
                            albumConfig,
                            sharedTransitionScope = this@SharedTransitionLayout,
                            animatedContentScope = this@composable
                        )
                    }
                    composable<Flow1000SectionPageParam> { backStackEntry ->
                        val sectionParam = backStackEntry.toRoute<Flow1000SectionPageParam>()
                        Flow1000SectionPage(
                            navController,
                            sectionParam,
                            sharedTransitionScope = this@SharedTransitionLayout,
                            animatedContentScope = this@composable
                        )
                    }
                    composable<LazyParam> { backStackEntry ->
                        val lazyParam = backStackEntry.toRoute<LazyParam>()
                        LazyPage(navController, lazyParam.pageId)
                    }
                    composable("greeting") { GreetingApp() }
                    composable("lazySample") { LazyStaggeredGridCustomScrollUsingLazyLayoutScrollScopeSample() }
                    composable("lazyScrollable") { LazyScrollable() }
                }
            }

        }
    }

}