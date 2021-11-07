package br.com.yujiyoshimine.profile.view

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.yujiyoshimine.commons.components.bottombox.BottomBox
import br.com.yujiyoshimine.commons.extensions.navigate
import br.com.yujiyoshimine.commons.theme.Purple
import br.com.yujiyoshimine.commons.theme.Red
import br.com.yujiyoshimine.commons.theme.Yellow
import br.com.yujiyoshimine.navigation.Screen
import br.com.yujiyoshimine.profile.R

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
