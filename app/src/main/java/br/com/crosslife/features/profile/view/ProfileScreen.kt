package br.com.crosslife.features.profile.view

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.Screen
import br.com.crosslife.extensions.navigate
import br.com.crosslife.ui.components.bottombox.BottomBox
import br.com.crosslife.ui.theme.Purple
import br.com.crosslife.ui.theme.Red
import br.com.crosslife.ui.theme.Yellow

@ExperimentalMaterialApi
@Composable
fun NavController.ProfileScreen() {
    ProfileBackground()
    ProfileBox()
}

@Composable
private fun NavController.ProfileBox() {
    BottomBox(title = "Lucas Yuji") {
        ProfileItem(
            Purple,
            painterResource(id = R.drawable.ic_strength),
            stringResource(id = R.string.advanced_profile),
        ) {
            navigate(Screen.DetailProfile)
        }
        ProfileItem(
            Yellow,
            painterResource(id = R.drawable.ic_lock),
            stringResource(id = R.string.change_password),
        ) {
            navigate(Screen.ChangePassword)
        }
        ProfileItem(
            Red,
            painterResource(id = R.drawable.ic_exit),
            stringResource(id = R.string.leave_account),
        ) {
            navigate(Screen.Logout)
        }
    }
}
