package br.com.crosslife

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.crosslife.features.login.view.LoginScreen
import br.com.crosslife.features.splash.view.SplashScreen

interface Router {

    companion object {

        @Composable
        fun Init() {
            val navController = rememberNavController()
            navController.apply {
                NavHost(this, startDestination = Route.Splash.path) {
                    route(Route.Splash) { SplashScreen() }
                    route(Route.Login) { LoginScreen() }
                    route(Route.Home) { Text(text = "Home") }
                }
            }
        }

        private fun NavGraphBuilder.route(
            route: Route,
            arguments: List<NamedNavArgument> = emptyList(),
            deepLinks: List<NavDeepLink> = emptyList(),
            content: @Composable (NavBackStackEntry) -> Unit
        ) {
            composable(route.path, arguments, deepLinks, content)
        }
    }
}

fun NavController.navigate(route: Route) {
    navigate(route.path)
}

fun NavController.navigateAndPop(route: Route, popUpUntil: Route) {
    navigate(route.path) {
        popUpTo(popUpUntil.path) { inclusive = true }
    }
}