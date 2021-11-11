package br.com.crosslife.commons.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Green,
    primaryVariant = DarkGreen,
    secondary = Green,
    background = Black,
    onPrimary = White,
    onSecondary = White,
    onSurface = SurfaceBlack,
)

private val LightColorPalette = lightColors(
    primary = Green,
    primaryVariant = DarkGreen,
    secondary = Green,
    background = White,
    onPrimary = Black,
    onSecondary = Black,
    onSurface = SurfaceWhite,
)

@Composable
fun CrossLifeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = getTypography(colors),
        shapes = Shapes,
        content = content,
    )
}
