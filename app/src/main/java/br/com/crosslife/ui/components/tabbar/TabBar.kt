package br.com.crosslife.ui.components.tabbar

import android.graphics.Rect
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import br.com.crosslife.Screen
import br.com.crosslife.ui.components.animation.FadeAnimation
import br.com.crosslife.ui.components.animation.VerticalSlideAnimation
import br.com.crosslife.ui.components.bottomnavigation.BottomNavigation
import br.com.crosslife.ui.components.bottomnavigation.Tab
import android.view.ViewTreeObserver


enum class Keyboard {
    Opened, Closed
}

@Composable
fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
    val view = LocalView.current
        DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                Keyboard.Opened
            } else {
                Keyboard.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}


@ExperimentalAnimationApi
@Composable
fun NavController.TabBar(content: @Composable () -> Unit) {
    val currentTab by currentTabAsState()
    val isKeyboardOpen by keyboardAsState()
    val isTabNone = currentTab != Tab.None && isKeyboardOpen != Keyboard.Opened

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