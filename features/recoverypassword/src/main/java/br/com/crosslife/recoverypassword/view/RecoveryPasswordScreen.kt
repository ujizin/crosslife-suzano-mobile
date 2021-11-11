package br.com.crosslife.recoverypassword.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.commons.R
import br.com.crosslife.commons.components.form.PasswordUI
import br.com.crosslife.commons.components.snackbar.SnackBarByState
import br.com.crosslife.commons.extensions.navigate
import br.com.crosslife.commons.extensions.rememberFlowWithLifecycle
import br.com.crosslife.domain.model.Result
import br.com.crosslife.navigation.Screen
import br.com.crosslife.recoverypassword.viewmodel.RecoveryPasswordViewModel

@ExperimentalAnimationApi
@Composable
fun NavController.RecoveryPasswordScreen(
    viewModel: RecoveryPasswordViewModel = hiltViewModel(),
) {
    val newPasswordState = rememberSaveable { mutableStateOf("") }
    val confirmNewPasswordState = rememberSaveable { mutableStateOf("") }
    val state by rememberFlowWithLifecycle(viewModel.recoveryPasswordState)
        .collectAsState(initial = Result.Initial)

    PasswordUI(
        newPasswordState = newPasswordState,
        confirmNewPasswordState = confirmNewPasswordState,
        isLoading = state is Result.Loading,
        resetFields = state is Result.Success,
        onChangePasswordClick = {
            viewModel.changePassword(newPasswordState.value, confirmNewPasswordState.value)
        }
    )

    SnackBarByState(state, R.string.changed_password)

    if (state is Result.Success) {
        LaunchedEffect(true) {
            navigate(Screen.Home, newTask = true)
        }
    }
}
