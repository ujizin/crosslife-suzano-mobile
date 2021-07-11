package br.com.crosslife

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.crosslife.features.chat.view.ChatScreen
import br.com.crosslife.features.home.view.HomeScreen
import br.com.crosslife.features.login.view.LoginScreen
import br.com.crosslife.features.profile.view.ProfileScreen
import br.com.crosslife.features.search.view.SearchScreen
import br.com.crosslife.features.splash.view.SplashScreen
import br.com.crosslife.ui.components.tabbar.TabBar
import com.google.accompanist.pager.ExperimentalPagerApi

interface Router {

    companion object {

        @ExperimentalFoundationApi
        @ExperimentalPagerApi
        @Composable
        fun Init() {
            val navController = rememberNavController()
            navController.apply {
                NavHost(this, startDestination = Route.Splash.path) {
                    route(Route.Splash) { SplashScreen() }
                    route(Route.Login) { LoginScreen() }
                    route(Route.Home) { TabBar { HomeScreen() } }
                    route(Route.Search) { TabBar { SearchScreen() }}
                    route(Route.Chat) { TabBar { ChatScreen() } }
                    route(Route.Profile) { TabBar { ProfileScreen() }}
                }
            }
        }

        private fun NavGraphBuilder.route(
            route: Route,
            arguments: List<NamedNavArgument> = emptyList(),
            deepLinks: List<NavDeepLink> = emptyList(),
            content: @Composable (NavBackStackEntry) -> Unit,
        ) {
            composable(route.path, arguments, deepLinks, content)
        }
    }
}

fun NavController.navigate(route: Route, singleTop: Boolean = false) {
    navigate(route.path) {
        launchSingleTop = singleTop
    }
}

fun NavController.navigateAndPop(route: Route, popUpUntil: Route) {
    navigate(route.path) {
        popUpTo(popUpUntil.path) { inclusive = true }
    }
}