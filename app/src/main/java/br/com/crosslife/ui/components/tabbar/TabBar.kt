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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.platform.LocalContext
import br.com.crosslife.R
import br.com.crosslife.extensions.navigate
import br.com.crosslife.features.home.view.HomeLogo
import br.com.crosslife.ui.theme.Space
import kotlinx.coroutines.launch


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
    val notHasTab = currentTab != Tab.None && isKeyboardOpen != Keyboard.Opened
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            VerticalSlideAnimation(visible = notHasTab) {
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
        drawerGesturesEnabled = notHasTab,
        drawerContent = {
            with(LocalContext.current) {
                HomeLogo()
                Spacer(Modifier.height(Space.BORDER))
                NavigationAction(
                    scaffoldState,
                    getString(R.string.menu_item_home),
                    Screen.HomeRoot
                )
                NavigationAction(
                    scaffoldState,
                    getString(R.string.menu_item_search),
                    Screen.SearchRoot
                )
                NavigationAction(
                    scaffoldState,
                    getString(R.string.menu_item_chat),
                    Screen.ChatRoot
                )
                NavigationAction(
                    scaffoldState,
                    getString(R.string.menu_item_profile),
                    Screen.ProfileRoot
                )
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

@Composable
fun NavController.NavigationAction(scaffoldState: ScaffoldState, label: String, screen: Screen) {
    val coroutineScope = rememberCoroutineScope()
    Text(
        text = label,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
                navigate(screen)
            }
            .padding(vertical = Space.XXXS, horizontal = Space.XXS)
    )
}