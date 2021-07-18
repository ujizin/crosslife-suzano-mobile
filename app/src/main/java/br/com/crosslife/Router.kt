package br.com.crosslife

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.crosslife.features.changepassword.view.ChangePasswordScreen
import br.com.crosslife.features.chat.view.ChatScreen
import br.com.crosslife.features.home.view.HomeScreen
import br.com.crosslife.features.login.view.LoginScreen
import br.com.crosslife.features.profile.view.DetailProfileScreen
import br.com.crosslife.features.profile.view.ProfileScreen
import br.com.crosslife.features.search.view.SearchScreen
import br.com.crosslife.features.splash.view.SplashScreen
import br.com.crosslife.ui.components.animation.FadeAnimation
import br.com.crosslife.ui.components.tabbar.TabBar
import com.google.accompanist.pager.ExperimentalPagerApi

interface Router {

    companion object {

        @ExperimentalAnimationApi
        @ExperimentalFoundationApi
        @ExperimentalPagerApi
        @Composable
        fun Init() {
            val navController = rememberNavController()
            navController.apply {
                TabBar {
                    NavHost(navController = this, startDestination = Screen.Root.route) {
                        navigation(
                            route = Screen.Root.route,
                            startDestination = Screen.Splash.route
                        ) {
                            route(Screen.Splash) { SplashScreen() }
                            route(Screen.Login) { LoginScreen() }
                        }
                        navigation(
                            route = Screen.HomeRoot.route,
                            startDestination = Screen.Home.route,
                        ) {
                            route(Screen.Home) { HomeScreen() }
                        }
                        navigation(
                            route = Screen.SearchRoot.route,
                            startDestination = Screen.Search.route
                        ) {
                            route(Screen.Search) { SearchScreen() }
                        }
                        navigation(
                            route = Screen.ChatRoot.route,
                            startDestination = Screen.Chat.route
                        ) {
                            route(Screen.Chat) { ChatScreen() }
                        }
                        navigation(
                            route = Screen.ProfileRoot.route,
                            startDestination = Screen.Profile.route,
                        ) {
                            route(Screen.Profile) { ProfileScreen() }
                            route(Screen.DetailProfile) { DetailProfileScreen() }
                            route(Screen.ChangePassword) { ChangePasswordScreen() }
                        }
                    }
                }
            }
        }

        @ExperimentalAnimationApi
        private fun NavGraphBuilder.route(
            screen: Screen,
            arguments: List<NamedNavArgument> = emptyList(),
            deepLinks: List<NavDeepLink> = emptyList(),
            content: @Composable (NavBackStackEntry) -> Unit,
        ) {
            composable(screen.route, arguments, deepLinks) {
                FadeAnimation {
                    content(it)
                }
            }
        }
    }
}

fun NavController.navigate(
    screen: Screen,
) {
    navigate(screen.route)
}

fun NavController.navigate(
    screen: Screen,
    block: NavOptionsBuilder.() -> Unit,
) {
    navigate(screen.route, block)
}

fun NavController.navigateAndPop(
    screen: Screen,
    popUpUntil: Screen,
    isStartDestination: Boolean = false,
) {
    navigate(screen.route) {
        popUpTo(popUpUntil.route) { inclusive = true }
        if (isStartDestination) {
            graph.setStartDestination(screen.route)
        }
    }
}