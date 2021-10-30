package br.com.crosslife.features.splash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.Screen
import br.com.crosslife.extensions.navigateAndPop
import br.com.crosslife.extensions.rememberFlowWithLifecycle
import br.com.crosslife.features.splash.viewmodel.SplashStateUi
import br.com.crosslife.features.splash.viewmodel.SplashViewModel

@Composable
fun NavController.SplashScreen(viewModel: SplashViewModel = hiltViewModel()) {
    val isAuthenticated by rememberFlowWithLifecycle(viewModel.isAuthenticated)
        .collectAsState(initial = SplashStateUi.Initial)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.crosslife_logo),
            contentDescription = "logo",
            modifier = Modifier
                .width(155.dp)
                .height(150.dp)
        )
    }

    redirect(isAuthenticated)
}

private fun NavController.redirect(isAuthenticated: SplashStateUi) {
    when (isAuthenticated) {
        SplashStateUi.Initial -> return
        SplashStateUi.Authenticated -> navigateAndPop(Screen.HomeRoot, Screen.Splash, true)
        SplashStateUi.NotAuthenticated -> navigateAndPop(Screen.Login, Screen.Splash)
    }
}