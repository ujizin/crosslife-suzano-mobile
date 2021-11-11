package br.com.crosslife.commons.components.snackbar

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import br.com.crosslife.domain.model.Result

@ExperimentalAnimationApi
@Composable
fun SnackBarByState(state: Result<*>, @StringRes successRes: Int) {
    when (val result: Result<*> = state) {
        is Result.Error -> SnackBarError(result.serverError)
        is Result.Success -> SnackBarSuccess(successRes)
        else -> Unit
    }
}