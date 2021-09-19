package br.com.crosslife.features.forgotpassword

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.components.Button
import br.com.crosslife.components.input.TextField
import br.com.crosslife.data.Result
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.features.changepassword.view.SnackBarSuccess
import br.com.crosslife.features.login.view.SnackBarError
import br.com.crosslife.features.login.viewmodel.LoginViewModel
import br.com.crosslife.ui.components.topbar.ScaffoldTopbar
import br.com.crosslife.ui.theme.Space

@ExperimentalAnimationApi
@Composable
fun NavController.ForgotPasswordScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val forgotPasswordState = rememberSaveable { mutableStateOf("") }
    val state by viewModel.forgotPassword.collectAsState()
    ScaffoldTopbar(titleRes = R.string.forgot_password) {
        Column(
            Modifier
                .padding(horizontal = Space.BORDER)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                stringResource(id = R.string.type_your_username).capitalize(),
                style = MaterialTheme.typography.h3
            )
            TextField(
                state = forgotPasswordState,
                label = stringResource(id = R.string.label_email),
                modifier = Modifier.padding(top = Space.XXS),
            )
            Button(
                Modifier
                    .fillMaxWidth()
                    .padding(top = Space.XXXS),
                textButton = stringResource(id = R.string.send),
                isLoading = state is Result.Loading,
                onClick = {
                    viewModel.fetchForgotPassword(forgotPasswordState.value)
                }
            )
        }
    }
    when (val result = state) {
        is Result.Error -> SnackBarError(result.serverError)
        is Result.Success -> SnackBarSuccess(R.string.forgot_password_sent)
        else -> Unit
    }
}
