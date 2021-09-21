package br.com.crosslife.features.profile.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.ui.components.topbar.ScaffoldTopbar

@ExperimentalAnimationApi
@Composable
fun NavController.DetailProfileScreen() {
    ScaffoldTopbar(titleRes = R.string.advanced_profile) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Em construção", style = MaterialTheme.typography.h3)
        }
    }
}