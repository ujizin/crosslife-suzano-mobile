package br.com.crosslife.features.changepassword.view

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.components.Button
import br.com.crosslife.components.input.TextField
import br.com.crosslife.components.snackbar.SnackBar
import br.com.crosslife.data.Result
import br.com.crosslife.features.changepassword.viewmodel.ChangePasswordViewModel
import br.com.crosslife.ui.components.snackbar.SnackBarByState
import br.com.crosslife.ui.components.topbar.ScaffoldTopbar
import br.com.crosslife.ui.theme.Space

@ExperimentalAnimationApi
@Composable
fun NavController.ChangePasswordScreen(viewModel: ChangePasswordViewModel = hiltViewModel()) {
    val oldPasswordState = rememberSaveable { mutableStateOf("") }
    val newPasswordState = rememberSaveable { mutableStateOf("") }
    val confirmNewPasswordState = rememberSaveable { mutableStateOf("") }
    val changePasswordState by viewModel.result.collectAsState()

    ChangePasswordUI(
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

@ExperimentalAnimationApi
@Composable
fun NavController.ChangePasswordUI(
    oldPasswordState: MutableState<String>? = null,
    newPasswordState: MutableState<String>,
    confirmNewPasswordState: MutableState<String>,
    isLoading: Boolean,
    resetFields: Boolean,
    onChangePasswordClick: () -> Unit,
) {
    ScaffoldTopbar(titleRes = R.string.change_password) {
        Column(
            modifier = Modifier
                .padding(horizontal = Space.BORDER)
                .verticalScroll(rememberScrollState()),
        ) {

            oldPasswordState?.let {
                ProfileTextField(
                    labelRes = R.string.old_password,
                    state = oldPasswordState,
                )
            }
            ProfileTextField(
                labelRes = R.string.new_password,
                state = newPasswordState,
            )
            ProfileTextField(
                labelRes = R.string.confirm_new_password,
                state = confirmNewPasswordState,
            )
            Button(
                textButton = stringResource(id = R.string.change),
                modifier = Modifier
                    .padding(top = Space.XXS)
                    .padding(bottom = Space.XXS)
                    .fillMaxWidth(),
                onClick = onChangePasswordClick,
                isLoading = isLoading
            )
        }
    }

    if (resetFields) {
        listOf(
            oldPasswordState,
            newPasswordState,
            confirmNewPasswordState
        ).clearAll()
    }
}

@ExperimentalAnimationApi
@Composable
fun SnackBarSuccess(@StringRes stringRes: Int) {
    SnackBar(
        text = stringResource(id = stringRes),
        durationMillis = SnackBar.TIME_LONG
    )
}

@Composable
private fun ProfileTextField(state: MutableState<String>, @StringRes labelRes: Int) {
    val modifier = Modifier.padding(top = Space.XXS)
    TextField(
        label = stringResource(id = labelRes),
        state = state,
        modifier = modifier,
        isPassword = true,
    )
}

fun List<MutableState<String>?>.clearAll() = forEach {
    it?.value = ""
}