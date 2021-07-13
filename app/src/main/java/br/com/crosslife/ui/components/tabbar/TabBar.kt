package br.com.crosslife.ui.components.tabbar

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import br.com.crosslife.ui.components.bottomnavigation.BottomNavigation

@ExperimentalAnimationApi
@Composable
fun TransitionFade(
    durationMillis: Int = 500,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    val visibleState = remember { MutableTransitionState(false) }
    visibleState.targetState = true
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            initialAlpha = 0.0f,
            animationSpec = tween(durationMillis = durationMillis)
        ),
        exit = fadeOut(
            animationSpec = tween(durationMillis = durationMillis)
        ),
        content = content,
    )
}

@ExperimentalAnimationApi
@Composable
fun NavController.TabBar(content: @Composable () -> Unit) {
    Scaffold(
        bottomBar = { BottomNavigation(this) },
        content = {
            TransitionFade {
                content()
            }
        }
    )
}