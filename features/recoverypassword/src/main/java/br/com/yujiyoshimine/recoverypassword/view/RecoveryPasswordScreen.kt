package br.com.yujiyoshimine.recoverypassword.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.yujiyoshimine.commons.R
import br.com.yujiyoshimine.commons.components.form.PasswordUI
import br.com.yujiyoshimine.commons.components.snackbar.SnackBarByState
import br.com.yujiyoshimine.commons.extensions.navigate
import br.com.yujiyoshimine.commons.extensions.rememberFlowWithLifecycle
import br.com.yujiyoshimine.domain.model.Result
import br.com.yujiyoshimine.navigation.Screen
import br.com.yujiyoshimine.recoverypassword.viewmodel.RecoveryPasswordViewModel

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
