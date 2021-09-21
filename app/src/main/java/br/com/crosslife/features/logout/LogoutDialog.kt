package br.com.crosslife.features.logout

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.Screen
import br.com.crosslife.components.Button
import br.com.crosslife.data.Result
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.extensions.navigateAndPop
import br.com.crosslife.features.login.viewmodel.LoginViewModel
import br.com.crosslife.ui.theme.Space

@Composable
fun NavController.LogoutDialog(userViewModel: LoginViewModel = hiltViewModel()) {
    val state by userViewModel.logout.collectAsState()
    when (state) {
        Result.Initial, Result.Loading -> LogoutScreen(userViewModel)
        else -> navigateAndPop(Screen.Login, Screen.Home, true)
    }
}

@Composable
fun LogoutScreen(userViewModel: LoginViewModel) {
    Column(
        Modifier
            .padding(Space.XXS)
            .fillMaxWidth(),
    ) {
        Text(
            stringResource(id = R.string.logout_title).capitalize(),
            style = MaterialTheme.typography.h3,
        )
        Text(
            stringResource(id = R.string.logout_description).capitalize(),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = Space.XS)
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = Space.XXS),
            horizontalArrangement = Arrangement.End,
        ) {
            Button(textButton = stringResource(id = R.string.logout).capitalize(), onClick = {
                userViewModel.fetchLogout()
            })
        }
    }
}
