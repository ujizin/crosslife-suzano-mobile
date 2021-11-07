package br.com.yujiyoshimine.forgotpassword.view

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
import br.com.yujiyoshimine.commons.components.topbar.ScaffoldTopbar
import br.com.yujiyoshimine.commons.components.Button
import br.com.yujiyoshimine.commons.components.input.TextField
import br.com.yujiyoshimine.commons.components.snackbar.SnackBarError
import br.com.yujiyoshimine.commons.components.snackbar.SnackBarSuccess
import br.com.yujiyoshimine.commons.extensions.capitalize
import br.com.yujiyoshimine.commons.extensions.rememberFlowWithLifecycle
import br.com.yujiyoshimine.commons.theme.Space
import br.com.yujiyoshimine.domain.model.Result
import br.com.yujiyoshimine.forgotpassword.R
import br.com.yujiyoshimine.forgotpassword.viewmodel.ForgotPasswordViewModel

@ExperimentalAnimationApi
@Composable
fun NavController.ForgotPasswordScreen(viewModel: ForgotPasswordViewModel = hiltViewModel()) {
    val forgotPasswordState = rememberSaveable { mutableStateOf("") }
    val state by rememberFlowWithLifecycle(viewModel.forgotPassword)
        .collectAsState(initial = Result.Initial)
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
                label = stringResource(id = R.string.username),
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
    when (val result: Result<*> = state) {
        is Result.Error -> SnackBarError(result.serverError)
        is Result.Success -> SnackBarSuccess(R.string.forgot_password_sent)
        else -> Unit
    }
}
