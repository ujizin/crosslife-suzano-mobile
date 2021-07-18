package br.com.crosslife.features.changepassword.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.components.Button
import br.com.crosslife.components.input.TextField
import br.com.crosslife.ui.components.topbar.ScaffoldTopbar
import br.com.crosslife.ui.theme.Space

@ExperimentalAnimationApi
@Composable
fun NavController.ChangePasswordScreen() {
    val oldPasswordState = remember { mutableStateOf("") }
    val newPasswordState = remember { mutableStateOf("") }
    val confirmNewPasswordState = remember { mutableStateOf("") }

    ScaffoldTopbar(titleRes = R.string.change_password) {
        val modifier = Modifier.padding(top = Space.XXS)
        Column(
            modifier = Modifier.padding(horizontal = Space.BORDER),
        ) {
            TextField(
                label = stringResource(id = R.string.old_password),
                state = oldPasswordState,
                modifier = modifier,
                isPassword = true,
            )
            TextField(
                label = stringResource(id = R.string.new_password),
                state = newPasswordState,
                modifier = modifier,
                isPassword = true,
            )
            TextField(
                label = stringResource(id = R.string.confirm_new_password),
                state = confirmNewPasswordState,
                modifier = modifier,
                isPassword = true,
            )
            Button(
                textButton = stringResource(id = R.string.change),
                modifier = modifier
                    .padding(top = Space.XS)
                    .fillMaxWidth(),
                onClick = {},
            )
        }
    }
}