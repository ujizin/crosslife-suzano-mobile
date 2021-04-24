package br.com.crosslife

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

interface Router {

    sealed class Route(val path: String) {
        companion object {
            private const val SPLASH_PATH = "splash"
            private const val LOGIN_PATH = "login"
            private const val HOME_PATH = "home"
        }

        object Splash : Route(SPLASH_PATH)
        object Login : Route(LOGIN_PATH)
        object Home : Route(HOME_PATH)
    }

    companion object {

        @Composable
        fun Init() {
            val navController = rememberNavController()
            NavHost(navController, startDestination = Route.Splash.path) {
                route(Route.Splash) {
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "Splash",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onPrimary
                        )
                    }
                }
                route(Route.Login) { Text(text = "Login") }
                route(Route.Home) { Text(text = "Home") }
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
