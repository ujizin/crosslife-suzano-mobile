package br.com.crosslife.features.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.Screen
import br.com.crosslife.components.Button
import br.com.crosslife.components.input.TextField
import br.com.crosslife.data.Result
import br.com.crosslife.features.login.viewmodel.LoginViewModel
import br.com.crosslife.navigateAndPop
import br.com.crosslife.ui.theme.Space

@Composable
fun NavController.LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val usernameState = rememberSaveable { mutableStateOf("") }
    val passwordState = rememberSaveable { mutableStateOf("") }
    val state by viewModel.login.collectAsState()
    when (state) {
        is Result.Success -> navigateAndPop(Screen.HomeRoot, Screen.Login, true)
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
                    label = stringResource(id = R.string.label_email),
                    state = usernameState,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
                TextField(
                    label = stringResource(id = R.string.label_password),
                    state = passwordState,
                    modifier = Modifier
                        .padding(top = Space.XS)
                        .fillMaxWidth(),
                    isPassword = true,
                )
                Button(
                    modifier = Modifier
                        .padding(top = Space.XXS)
                        .padding(bottom = Space.XXXS)
                        .fillMaxWidth(),
                    isLoading = state is Result.Loading,
                    onClick = {
                        viewModel.fetchLogin(usernameState.value, passwordState.value)
                    },
                    textButton = stringResource(id = R.string.enter),
                )
            }
        }
    }
}
