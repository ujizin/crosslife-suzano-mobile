package br.com.crosslife.features.profile.view

import androidx.compose.animation.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.crosslife.ui.components.animation.HorizontalSlideAnimation


@ExperimentalAnimationApi
@Composable
fun NavController.DetailProfileScreen() {
    HorizontalSlideAnimation {
        Text("Tela Perfil avançado!")
    }
}