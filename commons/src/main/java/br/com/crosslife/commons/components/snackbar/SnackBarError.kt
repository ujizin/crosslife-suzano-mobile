package br.com.crosslife.commons.components.snackbar

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import br.com.crosslife.commons.utils.ErrorHelper
import br.com.crosslife.domain.model.ServerError

@ExperimentalAnimationApi
@Composable
fun SnackBarError(error: ServerError) {
    val message = ErrorHelper.getMessageFromState(LocalContext.current, error)
    SnackBar(message, SnackBar.TIME_LONG, SnackBar.Error)
}
