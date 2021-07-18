package br.com.crosslife.ui.components.tabbar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import br.com.crosslife.Screen
import br.com.crosslife.ui.components.animation.FadeAnimation
import br.com.crosslife.ui.components.animation.VerticalSlideAnimation
import br.com.crosslife.ui.components.bottomnavigation.BottomNavigation
import br.com.crosslife.ui.components.bottomnavigation.Tab


@ExperimentalAnimationApi
@Composable
fun NavController.TabBar(content: @Composable () -> Unit) {

    Scaffold(
        bottomBar = {
            val currentTab by currentTabAsState()
            val isTabNone = currentTab != Tab.None

            VerticalSlideAnimation(visible = isTabNone) {
                BottomNavigation(currentTab) { screen ->
                    navigate(screen.route) {
                        launchSingleTop = true
                        restoreState = true
                        /*
                        *  TODO need to figure out how back to startDestination from nested navigation
                        *   when clicked twice
                        * */
                        popUpTo(graph.findStartDestination().id) {
                            saveState = true
                        }

                    }
                }
            }
        },
        content = {
            FadeAnimation {
                content()
            }
        }
    )
}

@Stable
@Composable
private fun NavController.currentTabAsState(): State<Tab> {
    val selectedItem = remember { mutableStateOf(Tab.None) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            val tabItem = Screen.findScreenByRoute(destination.route)?.tabItem ?: Tab.None
            selectedItem.value = tabItem
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}