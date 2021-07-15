package br.com.crosslife.features.changepassword.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.crosslife.ui.components.animation.HorizontalSlideAnimation

@ExperimentalAnimationApi
@Composable
fun NavController.ChangePasswordScreen() {
    HorizontalSlideAnimation {
        Text("Trocar senha")
    }
}