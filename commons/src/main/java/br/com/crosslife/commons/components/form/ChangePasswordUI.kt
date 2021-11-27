package br.com.crosslife.commons.components.form

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.crosslife.commons.R
import br.com.crosslife.commons.components.Button
import br.com.crosslife.commons.components.input.TextField
import br.com.crosslife.commons.components.topbar.ScaffoldTopbar
import br.com.crosslife.commons.theme.Space

@ExperimentalAnimationApi
@Composable
fun NavController.PasswordUI(
    oldPasswordState: MutableState<String>? = null,
    newPasswordState: MutableState<String>,
    confirmNewPasswordState: MutableState<String>,
    isLoading: Boolean,
    resetFields: Boolean,
    onChangePasswordClick: () -> Unit,
) {
    ScaffoldTopbar(title = stringResource(id = R.string.change_password)) {
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