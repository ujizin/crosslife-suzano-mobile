package br.com.crosslife.features.splash.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.crosslife.features.splash.viewmodel.SplashViewModel

@Composable
fun SplashScreen(viewModel: SplashViewModel = hiltViewModel()) {
    Text(text = "Hello world")
}
