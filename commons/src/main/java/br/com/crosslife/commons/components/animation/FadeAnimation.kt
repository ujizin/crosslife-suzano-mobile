package br.com.crosslife.commons.components.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@ExperimentalAnimationApi
@Composable
fun FadeAnimation(
    modifier: Modifier = Modifier,
    visible: Boolean = true,
    durationMillis: Int = 500,
    delay: Boolean = false,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    val visibleState = remember { MutableTransitionState(false) }
    visibleState.targetState = visible
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            initialAlpha = 0.0f,
            animationSpec = tween(
                delayMillis = if (delay) durationMillis else 0,
                durationMillis = durationMillis,
            )
        ),
        exit = fadeOut(
            animationSpec = tween(
                delayMillis = if (delay) durationMillis else 0,
                durationMillis = durationMillis,
            )
        ),
        modifier = modifier,
        content = content,
    )
}
