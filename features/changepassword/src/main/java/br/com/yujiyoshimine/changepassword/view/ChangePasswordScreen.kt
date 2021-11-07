package br.com.yujiyoshimine.changepassword.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.yujiyoshimine.changepassword.viewmodel.ChangePasswordViewModel
import br.com.yujiyoshimine.commons.R
import br.com.yujiyoshimine.commons.components.form.PasswordUI
import br.com.yujiyoshimine.commons.components.snackbar.SnackBarByState
import br.com.yujiyoshimine.commons.extensions.rememberFlowWithLifecycle
import br.com.yujiyoshimine.domain.model.Result

@ExperimentalAnimationApi
@Composable
fun NavController.ChangePasswordScreen(viewModel: ChangePasswordViewModel = hiltViewModel()) {
    val oldPasswordState = rememberSaveable { mutableStateOf("") }
    val newPasswordState = rememberSaveable { mutableStateOf("") }
    val confirmNewPasswordState = rememberSaveable { mutableStateOf("") }
    val changePasswordState by rememberFlowWithLifecycle(viewModel.result)
        .collectAsState(initial = Result.Initial)

    PasswordUI(
        oldPasswordState = oldPasswordState,
        newPasswordState = newPasswordState,
        confirmNewPasswordState = confirmNewPasswordState,
        isLoading = changePasswordState is Result.Loading,
        resetFields = changePasswordState is Result.Success,
        onChangePasswordClick = {
            viewModel.changePassword(
                oldPasswordState.value,
                newPasswordState.value,
                confirmNewPasswordState.value,
            )
        }
    )

    SnackBarByState(changePasswordState, R.string.changed_password)
}

fun List<MutableState<String>?>.clearAll() = forEach {
    it?.value = ""
}