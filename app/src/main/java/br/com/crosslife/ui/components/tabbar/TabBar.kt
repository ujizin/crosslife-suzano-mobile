package br.com.crosslife.ui.components.tabbar

import androidx.compose.animation.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.crosslife.ui.components.animation.FadeAnimation
import br.com.crosslife.ui.components.animation.VerticalSlideAnimation
import br.com.crosslife.ui.components.bottomnavigation.BottomNavigation
import br.com.crosslife.ui.components.bottomnavigation.Tab


@ExperimentalAnimationApi
@Composable
fun NavController.TabBar(currentTab: Tab, content: @Composable () -> Unit) {
    Scaffold(
        bottomBar = {
            VerticalSlideAnimation(visible = currentTab != Tab.None) {
                BottomNavigation(currentTab)
            }
        },
        content = {
            FadeAnimation {
                content()
            }
        }
    )
}