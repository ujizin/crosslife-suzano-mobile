package br.com.crosslife.commons.components.topbar

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import br.com.crosslife.commons.R
import br.com.crosslife.commons.components.animation.FadeAnimation
import br.com.crosslife.commons.components.animation.Slide
import br.com.crosslife.commons.components.animation.VerticalSlideAnimation
import br.com.crosslife.commons.extensions.capitalize

@ExperimentalAnimationApi
@Composable
fun NavController.ScaffoldTopbar(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int? = null,
    overlapTopBar: Boolean = false,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier,
    ) {
        VerticalSlideAnimation(
            anim = Slide.UpToDown,
        ) {
            ColumnIf(!overlapTopBar) {
                TopBar(titleRes, actions)
                FadeAnimation(delay = true) {
                    content()
                }
            }
        }
    }
}

@Composable
private fun NavController.TopBar(
    @StringRes titleRes: Int? = null,
    actions: @Composable (RowScope.() -> Unit),
) {
    TopAppBar(
        modifier = Modifier.zIndex(2F),
        title = {
            val title = titleRes ?: return@TopAppBar
            Text(
                stringResource(id = title).capitalize(),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
            )
        },
        navigationIcon = {
            IconButton(onClick = { navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(
                        id = R.string.back_pressed,
                    ),
                    tint = MaterialTheme.colors.onPrimary,
                )
            }
        },
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        actions = actions
    )
}

@Composable
fun ColumnIf(condition: Boolean, block: @Composable () -> Unit) {
    if (condition) Column(content = { block() }) else block()
}