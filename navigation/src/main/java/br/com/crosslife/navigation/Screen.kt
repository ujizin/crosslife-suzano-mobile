package br.com.crosslife.navigation

import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import androidx.navigation.navDeepLink
import br.com.crosslife.navigation.Screen.Conversation.USERNAME_ARG
import br.com.crosslife.navigation.Screen.RecoveryPassword.TOKEN_ARG


sealed class Screen(
    val route: String,
    val tabItem: Tab = Tab.Home,
    val arguments: List<NamedNavArgument> = listOf(),
    val deepLinks: List<NavDeepLink> = listOf(),
) {
    object Splash : Screen(SPLASH_PATH, Tab.None)
    object Login : Screen(LOGIN_PATH, Tab.None)
    object ForgotPassword : Screen(FORGOT_PASSWORD_PATH, Tab.None)
    object RecoveryPassword : Screen(
        route = RECOVERY_PASSWORD_PATH,
        tabItem = Tab.None,
        arguments = listOf(navArgument("token") { type = NavType.StringType }),
        deepLinks = listOf(navDeepLink {
            uriPattern = "${BuildConfig.APP_URL}/$RECOVERY_PASSWORD_PATH"
        })
    ) {
        const val TOKEN_ARG = "token"
    }


    object Home : Screen(HOME_PATH, Tab.Home)
    object Search : Screen(SEARCH_PATH, Tab.Search)
    object Chat : Screen(CHAT_PATH, Tab.Chat)
    object Conversation : Screen(
        CONVERSATION_PATH,
        Tab.None,
        listOf(navArgument("username") { type = NavType.StringType })
    ) {
        const val USERNAME_ARG = "username"

        fun getRoute(username: String): String {
            return "conversation/$username"
        }
    }

    object Profile : Screen(PROFILE_PATH, Tab.Profile)
    object DetailProfile : Screen(DETAIL_PROFILE_PATH, Tab.None)
    object ChangePassword : Screen(CHANGE_PASSWORD_PATH, Tab.None)

    object Root : Screen(ROOT_PATH)
    object HomeRoot : Screen(HOME_ROOT_PATH)
    object SearchRoot : Screen(SEARCH_ROOT_PATH)
    object ProfileRoot : Screen(PROFILE_ROOT_PATH)
    object ChatRoot : Screen(CHAT_ROOT_PATH)

    object DetailItem : Screen(DETAIL_ITEM_PATH, Tab.None)

    object Logout : Screen(LOGOUT_PATH, Tab.Profile)

    companion object {
        private const val SPLASH_PATH = "splash"
        private const val LOGIN_PATH = "login"
        private const val FORGOT_PASSWORD_PATH = "forgot_password"

        private const val LOGOUT_PATH = "logout"
        private const val RECOVERY_PASSWORD_PATH = "recovery_password?token={$TOKEN_ARG}"

        // Tabs
        private const val ROOT_PATH = "root"
        private const val HOME_ROOT_PATH = "home_root"
        private const val SEARCH_ROOT_PATH = "search_root"
        private const val CHAT_ROOT_PATH = "chat_root"
        private const val PROFILE_ROOT_PATH = "profile_root"
        // End Tabs

        private const val DETAIL_ITEM_PATH = "detail_item"

        private const val HOME_PATH = "home"
        private const val SEARCH_PATH = "search"
        private const val CHAT_PATH = "chat"
        private const val CONVERSATION_PATH = "conversation/{$USERNAME_ARG}"

        // Profile
        private const val PROFILE_PATH = "profile"
        private const val DETAIL_PROFILE_PATH = "$PROFILE_PATH/student"
        private const val CHANGE_PASSWORD_PATH = "$PROFILE_PATH/change-password"
        // End Profile

        fun findScreenByRoute(route: String?) =
            Screen::class.sealedSubclasses.firstOrNull {
                it.objectInstance?.route == route
            }?.objectInstance
    }
}