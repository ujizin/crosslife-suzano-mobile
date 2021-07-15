package br.com.crosslife.ui.components.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@ExperimentalAnimationApi
@Composable
fun VerticalSlideAnimation(
    visible: Boolean = true,
    durationMillis: Int = 500,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    val visibleState = remember { MutableTransitionState(false) }
    visibleState.targetState = visible
    AnimatedVisibility(
        visibleState = visibleState,
        enter = slideInVertically(
            initialOffsetY = { it * 2 },
            animationSpec = tween(durationMillis = durationMillis)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(durationMillis = durationMillis)
        ),
        content = content,
    )
}
