package br.com.crosslife.features.recoverypassword

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.Screen
import br.com.crosslife.data.Result
import br.com.crosslife.extensions.navigate
import br.com.crosslife.features.changepassword.view.ChangePasswordUI
import br.com.crosslife.features.changepassword.view.SnackBarByState
import br.com.crosslife.features.recoverypassword.viewmodel.RecoveryPasswordViewModel

@ExperimentalAnimationApi
@Composable
fun NavController.RecoveryPasswordScreen(
    viewModel: RecoveryPasswordViewModel = hiltViewModel(),
) {
    val newPasswordState = rememberSaveable { mutableStateOf("") }
    val confirmNewPasswordState = rememberSaveable { mutableStateOf("") }
    val state by viewModel.recoveryPasswordState.collectAsState()

    ChangePasswordUI(
        newPasswordState = newPasswordState,
        confirmNewPasswordState = confirmNewPasswordState,
        isLoading = state is Result.Loading,
        resetFields = state is Result.Success,
        onChangePasswordClick = {
            viewModel.changePassword(newPasswordState.value, confirmNewPasswordState.value)
        }
    )

    SnackBarByState(state)

    if (state is Result.Success) {
        LaunchedEffect(true) {
            navigate(Screen.Home, newTask = true)
        }
    }
}
