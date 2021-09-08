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
import br.com.crosslife.ui.components.topbar.ScaffoldTopbar
import br.com.crosslife.ui.theme.Space

@ExperimentalAnimationApi
@Composable
fun NavController.ChangePasswordScreen(viewModel: ChangePasswordViewModel = hiltViewModel()) {
    val oldPasswordState = rememberSaveable { mutableStateOf("") }
    val newPasswordState = rememberSaveable { mutableStateOf("") }
    val confirmNewPasswordState = rememberSaveable { mutableStateOf("") }
    val changePasswordState by viewModel.result.collectAsState()
    when (changePasswordState) {
        is Result.Success -> ChangePasswordSuccessScreen()
        else -> ScaffoldTopbar(titleRes = R.string.change_password) {
            Column(
                modifier = Modifier
                    .padding(horizontal = Space.BORDER)
                    .verticalScroll(rememberScrollState()),
            ) {
                ProfileTextField(
                    labelRes = R.string.old_password,
                    state = oldPasswordState,
                )
                ProfileTextField(
                    labelRes = R.string.confirm_new_password,
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
                    onClick = {
                        viewModel.changePassword(oldPasswordState.value, newPasswordState.value)
                    },
                    isLoading = changePasswordState is Result.Loading
                )
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ChangePasswordSuccessScreen() {
    SnackBar(
        text = stringResource(id = R.string.changed_password),
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