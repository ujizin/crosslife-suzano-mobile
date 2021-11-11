package br.com.crosslife.logout.view

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
import br.com.crosslife.commons.components.Button
import br.com.crosslife.commons.extensions.capitalize
import br.com.crosslife.commons.extensions.navigateAndPop
import br.com.crosslife.commons.extensions.rememberFlowWithLifecycle
import br.com.crosslife.commons.theme.Space
import br.com.crosslife.domain.model.Result
import br.com.crosslife.logout.R
import br.com.crosslife.logout.viewmodel.LogoutViewModel
import br.com.crosslife.navigation.Screen

@Composable
fun NavController.LogoutDialog(userViewModel: LogoutViewModel = hiltViewModel()) {
    val state by rememberFlowWithLifecycle(userViewModel.logout)
        .collectAsState(initial = Result.Initial)
    when (state) {
        Result.Initial, Result.Loading -> LogoutScreen(userViewModel)
        else -> navigateAndPop(Screen.Login, Screen.Home, true)
    }
}

@Composable
fun LogoutScreen(userViewModel: LogoutViewModel) {
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
