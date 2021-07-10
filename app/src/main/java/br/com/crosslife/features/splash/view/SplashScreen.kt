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
import br.com.crosslife.Route
import br.com.crosslife.features.splash.viewmodel.SplashResult
import br.com.crosslife.features.splash.viewmodel.SplashViewModel
import br.com.crosslife.navigate
import br.com.crosslife.navigateAndPop

@Composable
fun NavController.SplashScreen(viewModel: SplashViewModel = hiltViewModel()) {
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

    val isAuthenticated by viewModel.isAuthenticated.collectAsState()
    when (isAuthenticated) {
        SplashResult.Initial -> return
        SplashResult.Authenticated -> navigateAndPop(Route.Home, Route.Splash)
        SplashResult.NotAuthenticated -> navigateAndPop(Route.Login, Route.Splash)
    }
}
