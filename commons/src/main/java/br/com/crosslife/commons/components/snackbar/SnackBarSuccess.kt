
package br.com.crosslife.commons.components.snackbar

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@ExperimentalAnimationApi
@Composable
fun SnackBarSuccess(@StringRes stringRes: Int) {
    SnackBar(
        text = stringResource(id = stringRes),
        durationMillis = SnackBar.TIME_LONG
    )
}
