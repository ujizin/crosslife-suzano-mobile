package br.com.crosslife

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import br.com.crosslife.ui.components.bottomnavigation.Tab
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
            val currentTab = remember { mutableStateOf(Tab.None) }
            navController.apply {
                TabBar(currentTab.value) {
                    NavHost(this, startDestination = Route.Splash.path) {
                        Pair(this, currentTab).apply {
                            route(Route.Splash) { SplashScreen() }
                            route(Route.Login) { LoginScreen() }
                            route(Route.Home) { HomeScreen() }
                            route(Route.Search) { SearchScreen() }
                            route(Route.Chat) { ChatScreen() }
                            route(Route.Profile) { ProfileScreen() }
                            route(Route.DetailProfile) { DetailProfileScreen() }
                            route(Route.ChangePassword) { ChangePasswordScreen() }
                        }
                    }
                }
            }
        }

        @ExperimentalAnimationApi
        private fun Pair<NavGraphBuilder, MutableState<Tab>>.route(
            route: Route,
            arguments: List<NamedNavArgument> = emptyList(),
            deepLinks: List<NavDeepLink> = emptyList(),
            content: @Composable (NavBackStackEntry) -> Unit,
        ) {
            val (navGraph, currentTabState) = this
            navGraph.composable(route.path, arguments, deepLinks) {
                currentTabState.value = route.tabItem
                FadeAnimation {
                    content(it)
                }
            }
        }
    }
}

fun NavController.navigate(
    route: Route,
    block: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(route.path, block)
}

fun NavController.navigateAndPop(route: Route, popUpUntil: Route) {
    navigate(route.path) {
        popUpTo(popUpUntil.path) { inclusive = true }
    }
}