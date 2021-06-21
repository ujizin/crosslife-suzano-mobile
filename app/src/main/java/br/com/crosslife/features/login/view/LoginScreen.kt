package br.com.crosslife.features.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.components.Button
import br.com.crosslife.components.input.TextField
import br.com.crosslife.features.login.viewmodel.LoginViewModel

@Composable
fun NavController.LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val usernameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.crosslife_logo),
            contentDescription = stringResource(id = R.string.app_logo),
            modifier = Modifier
                .padding(top = 20.dp)
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
                .padding(top = 8.dp)
                .fillMaxWidth(),
            isPassword = true,
        )
        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            onClick = {},
            textButton = stringResource(id = R.string.enter),
        )
    }
}
