package br.com.crosslife.features.logout

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.Screen
import br.com.crosslife.components.Button
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.navigateAndPop
import br.com.crosslife.ui.theme.Space

@Composable
fun NavController.LogoutDialog() {
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
                navigateAndPop(Screen.Login, Screen.Home, true)
            })
        }
    }
}
