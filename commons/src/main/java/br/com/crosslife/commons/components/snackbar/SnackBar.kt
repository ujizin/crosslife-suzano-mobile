package br.com.crosslife.commons.components.snackbar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import br.com.crosslife.commons.components.animation.Slide
import br.com.crosslife.commons.components.animation.VerticalSlideAnimation
import br.com.crosslife.commons.theme.Green
import br.com.crosslife.commons.theme.Red
import br.com.crosslife.commons.theme.Yellow
import kotlinx.coroutines.delay

@ExperimentalAnimationApi
@Stable
@Composable
fun SnackBar(
    text: String,
    durationMillis: Long,
    type: SnackBar = SnackBar.Success,
) {
    val visibleState = rememberSaveable { mutableStateOf(true) }
    LaunchedEffect(visibleState) {
        delay(durationMillis)
        visibleState.value = false
    }

    VerticalSlideAnimation(
        visible = visibleState.value,
        anim = Slide.UpToDown,
    ) {
        Snackbar(
            backgroundColor = type.color,
            shape = RectangleShape
        ) {
            Text(text, style = MaterialTheme.typography.body1)
        }
    }
}

enum class SnackBar(val color: Color) {
    Success(Green),
    Warning(Yellow),
    Error(Red);

    companion object {
        const val TIME_LONG = 2000L
        const val TIME_SHORT = 1000L
    }
}