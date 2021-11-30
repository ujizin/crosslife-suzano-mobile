package br.com.crosslife.login.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults.outlinedButtonColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.commons.R
import br.com.crosslife.commons.components.Button
import br.com.crosslife.commons.components.input.TextField
import br.com.crosslife.commons.components.snackbar.SnackBar
import br.com.crosslife.commons.components.snackbar.SnackBarError
import br.com.crosslife.commons.extensions.navigate
import br.com.crosslife.commons.extensions.rememberFlowWithLifecycle
import br.com.crosslife.commons.theme.Space
import br.com.crosslife.login.viewmodel.LoginViewModel
import br.com.crosslife.login.viewmodel.LoginViewState
import br.com.crosslife.navigation.Screen
import br.com.crosslife.navigation.extensions.navigateAndPop

@ExperimentalAnimationApi
@Composable
fun NavController.LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val usernameState = rememberSaveable { mutableStateOf("") }
    val passwordState = rememberSaveable { mutableStateOf("") }
    val state by rememberFlowWithLifecycle(viewModel.login)
        .collectAsState(initial = LoginViewState.Initial)

    when (state) {
        is LoginViewState.Success -> navigateAndPop(Screen.HomeRoot, Screen.Login, true)
        else -> {
            Column(
                modifier = Modifier
                    .padding(horizontal = Space.BORDER)
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(R.drawable.crosslife_logo),
                    contentDescription = stringResource(id = R.string.app_logo),
                    modifier = Modifier
                        .padding(top = Space.XXXS)
                        .width(155.dp)
                        .height(150.dp)
                )
                TextField(
                    label = stringResource(id = R.string.username),
                    state = usernameState,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
                TextField(
                    label = stringResource(id = R.string.password),
                    state = passwordState,
                    modifier = Modifier
                        .padding(top = Space.XS)
                        .fillMaxWidth(),
                    isPassword = true,
                )
                Button(
                    modifier = Modifier
                        .padding(top = Space.XXS)
                        .fillMaxWidth(),
                    isLoading = state is LoginViewState.Loading,
                    onClick = {
                        viewModel.fetchLogin(usernameState.value, passwordState.value)
                    },
                    textButton = stringResource(id = R.string.enter),
                )
                OutlinedButton(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 40.dp)
                        .padding(top = Space.XS)
                        .padding(bottom = Space.XXXS)
                        .fillMaxWidth(),
                    colors = outlinedButtonColors(
                        backgroundColor = Color.Transparent,
                    ),
                    border = BorderStroke(0.dp, Color.Transparent),
                    onClick = {
                        navigate(Screen.ForgotPassword)
                    }) {
                    Text(
                        stringResource(id = R.string.forgot_password),
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                    )
                }
            }
            when (val result: LoginViewState = state) {
                is LoginViewState.Error -> SnackBarError(result.error)
                LoginViewState.InvalidAccount -> SnackBar(
                    stringResource(id = R.string.invalid_account_or_password),
                    SnackBar.TIME_LONG,
                    SnackBar.Error)
                else -> Unit
            }
        }
    }
}
