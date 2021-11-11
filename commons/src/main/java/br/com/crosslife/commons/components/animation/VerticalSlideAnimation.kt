package br.com.crosslife.commons.components.animation

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
    anim: Slide = Slide.DownToUp,
    content: @Composable AnimatedVisibilityScope.() -> Unit,
) {
    val visibleState = remember { MutableTransitionState(false) }
    visibleState.targetState = visible
    AnimatedVisibility(
        visibleState = visibleState,
        enter = slideInVertically(
            initialOffsetY = { anim.getInitialOffSetY(it) },
            animationSpec = tween(durationMillis = durationMillis)
        ),
        exit = slideOutVertically(
            targetOffsetY = { anim.getTargetOffSetY(it) },
            animationSpec = tween(durationMillis = durationMillis)
        ),
        content = content,
    )
}

enum class Slide(private val initial: Int, private val target: Int,) {
    UpToDown(-1, -1), DownToUp(2, 2);

    fun getInitialOffSetY(offset: Int): Int = offset * initial
    fun getTargetOffSetY(offset: Int): Int = offset * target
}