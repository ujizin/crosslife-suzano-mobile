package br.com.crosslife

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import br.com.yujiyoshimine.changepassword.view.ChangePasswordScreen
import br.com.yujiyoshimine.chat.view.ChatScreen
import br.com.yujiyoshimine.commons.components.animation.FadeAnimation
import br.com.yujiyoshimine.commons.components.tabbar.TabBar
import br.com.yujiyoshimine.detail.DetailScreen
import br.com.yujiyoshimine.domain.model.DetailItem
import br.com.yujiyoshimine.forgotpassword.view.ForgotPasswordScreen
import br.com.yujiyoshimine.home.view.HomeScreen
import br.com.yujiyoshimine.login.view.LoginScreen
import br.com.yujiyoshimine.logout.view.LogoutDialog
import br.com.yujiyoshimine.navigation.Screen
import br.com.yujiyoshimine.profile.view.DetailProfileScreen
import br.com.yujiyoshimine.profile.view.ProfileScreen
import br.com.yujiyoshimine.recoverypassword.view.RecoveryPasswordScreen
import br.com.yujiyoshimine.search.view.SearchScreen
import br.com.yujiyoshimine.splash.view.SplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi

interface Router {

    companion object {

        @ExperimentalPagerApi
        @ExperimentalMaterialApi
        @ExperimentalAnimationApi
        @ExperimentalFoundationApi
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
                            route(Screen.ForgotPassword) { ForgotPasswordScreen() }
                            route(Screen.RecoveryPassword) { RecoveryPasswordScreen() }
                        }
                        navigation(
                            route = Screen.HomeRoot.route,
                            startDestination = Screen.Home.route,
                        ) {
                            route(Screen.Home) { HomeScreen() }
                            route(
                                screen = Screen.DetailItem
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
            content: @Composable (NavBackStackEntry) -> Unit,
        ) {
            composable(screen.route, screen.arguments, screen.deepLinks) {
                FadeAnimation {
                    content(it)
                }
            }
        }
    }
}