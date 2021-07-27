package br.com.crosslife

import br.com.crosslife.ui.components.bottomnavigation.Tab


sealed class Screen(val route: String, val tabItem: Tab = Tab.Home) {
    object Splash : Screen(SPLASH_PATH, Tab.None)
    object Login : Screen(LOGIN_PATH, Tab.None)
    object ForgotPassword : Screen(FORGOT_PASSWORD_PATH, Tab.None)

    object Home : Screen(HOME_PATH, Tab.Home)
    object Search : Screen(SEARCH_PATH, Tab.Search)
    object Chat : Screen(CHAT_PATH, Tab.Chat)
    object Profile : Screen(PROFILE_PATH, Tab.Profile)
    object DetailProfile : Screen(DETAIL_PROFILE_PATH, Tab.None)
    object ChangePassword : Screen(CHANGE_PASSWORD_PATH, Tab.None)

    object Root : Screen(ROOT_PATH)
    object HomeRoot : Screen(HOME_ROOT_PATH)
    object SearchRoot : Screen(SEARCH_ROOT_PATH)
    object ProfileRoot : Screen(PROFILE_ROOT_PATH)
    object ChatRoot : Screen(CHAT_ROOT_PATH)

    object WeeklyTrain : Screen("$WEEKLY_TRAIN_PATH/{id}") {
        fun createRoute(id: Int) = "$WEEKLY_TRAIN_PATH/$id"
    }

    object Logout : Screen(LOGOUT_PATH, Tab.Profile)

    companion object {
        private const val SPLASH_PATH = "splash"
        private const val LOGIN_PATH = "login"
        private const val FORGOT_PASSWORD_PATH = "forgot_password"
        private const val LOGOUT_PATH = "logout"

        // Tabs
        private const val ROOT_PATH = "root"
        private const val HOME_ROOT_PATH = "home_root"
        private const val SEARCH_ROOT_PATH = "search_root"
        private const val CHAT_ROOT_PATH = "chat_root"
        private const val PROFILE_ROOT_PATH = "profile_root"
        // End Tabs

        private const val WEEKLY_TRAIN_PATH = "weekly_train"

        private const val HOME_PATH = "home"
        private const val SEARCH_PATH = "search"
        private const val CHAT_PATH = "chat"

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