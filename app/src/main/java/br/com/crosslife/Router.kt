package br.com.crosslife

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import br.com.crosslife.domain.models.DetailItem
import br.com.crosslife.features.changepassword.view.ChangePasswordScreen
import br.com.crosslife.features.chat.view.ChatScreen
import br.com.crosslife.features.forgotpassword.ForgotPasswordScreen
import br.com.crosslife.features.home.view.HomeScreen
import br.com.crosslife.features.login.view.LoginScreen
import br.com.crosslife.features.logout.LogoutDialog
import br.com.crosslife.features.profile.view.DetailProfileScreen
import br.com.crosslife.features.profile.view.ProfileScreen
import br.com.crosslife.features.search.view.SearchScreen
import br.com.crosslife.features.splash.view.SplashScreen
import br.com.crosslife.features.detailscreen.DetailScreen
import br.com.crosslife.ui.components.animation.FadeAnimation
import br.com.crosslife.ui.components.tabbar.TabBar
import com.google.accompanist.pager.ExperimentalPagerApi

interface Router {

    companion object {

        @ExperimentalMaterialApi
        @ExperimentalAnimationApi
        @ExperimentalFoundationApi
        @ExperimentalPagerApi
        @Composable
        fun Init(navController: NavController, startDestination: String) {
            navController.apply {
                TabBar {
                    NavHost(
                        navController = this as NavHostController,
                        startDestination = startDestination,
                    ) {
                        navigation(
                            route = Screen.Root.route,
                            startDestination = Screen.Splash.route
                        ) {
                            route(Screen.Splash) { SplashScreen() }
                            route(Screen.Login) { LoginScreen() }
                            route(Screen.ForgotPassword, deepLinks = listOf(
                                navDeepLink { uriPattern = "https://www.crosslife.com.br/"}
                            )) { ForgotPasswordScreen() }
                        }
                        navigation(
                            route = Screen.HomeRoot.route,
                            startDestination = Screen.Home.route,
                        ) {
                            route(Screen.Home) { HomeScreen() }
                            route(
                                screen = Screen.WeeklyTrain
                            ) {
                                val detailItem = previousBackStackEntry
                                    ?.arguments?.getParcelable<DetailItem>("detail_item")
                                DetailScreen(detailItem)
                            }
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
                        dialog(Screen.Logout.route) { LogoutDialog() }
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