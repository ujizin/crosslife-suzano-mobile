package br.com.crosslife.ui.components.tabbar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import br.com.crosslife.Screen
import br.com.crosslife.ui.components.animation.FadeAnimation
import br.com.crosslife.ui.components.animation.VerticalSlideAnimation
import br.com.crosslife.ui.components.bottomnavigation.BottomNavigation
import br.com.crosslife.ui.components.bottomnavigation.Tab
import br.com.crosslife.ui.theme.Space


@ExperimentalAnimationApi
@Composable
fun NavController.TabBar(content: @Composable () -> Unit) {
    val currentTab by currentTabAsState()
    val isTabNone = currentTab != Tab.None
    Scaffold(
        bottomBar = {
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
        content = { innerPadding ->
            FadeAnimation(modifier = Modifier.padding(innerPadding)) {
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