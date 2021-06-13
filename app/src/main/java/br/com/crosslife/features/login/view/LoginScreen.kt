package br.com.crosslife.features.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
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
import br.com.crosslife.components.TextField
import br.com.crosslife.features.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val usernameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.crosslife_logo),
            contentDescription = stringResource(id = R.string.app_logo),
            modifier = Modifier
                .width(155.dp)
                .height(150.dp)
                .weight(1F)
        )
        Column(Modifier.weight(1F)) {
            TextField(
                label = stringResource(id = R.string.label_email),
                state = usernameState,
                modifier = Modifier
                    .fillMaxWidth()
            )
            TextField(
                label = stringResource(id = R.string.label_password),
                state = passwordState,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            )
            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                onClick = {},
                textButton = stringResource(id = R.string.enter),
            )
        }
    }
}
