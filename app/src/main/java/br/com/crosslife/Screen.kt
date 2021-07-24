package br.com.crosslife

import br.com.crosslife.ui.components.bottomnavigation.Tab


enum class Screen(val route: String, val tabItem: Tab = Tab.Home) {
    Splash(Screen.SPLASH_PATH, Tab.None),
    Login(Screen.LOGIN_PATH, Tab.None),
    ForgotPassword(Screen.FORGOT_PASSWORD_PATH, Tab.None),

    Home(Screen.HOME_PATH, Tab.Home),
    Search(Screen.SEARCH_PATH, Tab.Search),
    Chat(Screen.CHAT_PATH, Tab.Chat),
    Profile(Screen.PROFILE_PATH, Tab.Profile),
    DetailProfile(Screen.DETAIL_PROFILE_PATH, Tab.None),
    ChangePassword(Screen.CHANGE_PASSWORD_PATH, Tab.None),

    Root(Screen.ROOT_PATH),
    HomeRoot(Screen.HOME_ROOT_PATH),
    SearchRoot(Screen.SEARCH_ROOT_PATH),
    ProfileRoot(Screen.PROFILE_ROOT_PATH),
    ChatRoot(Screen.CHAT_ROOT_PATH),

    Logout(Screen.LOGOUT_PATH, Tab.Profile);

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

        private const val HOME_PATH = "home"
        private const val SEARCH_PATH = "search"
        private const val CHAT_PATH = "chat"

        // Profile
        private const val PROFILE_PATH = "profile"
        private const val DETAIL_PROFILE_PATH = "$PROFILE_PATH/student"
        private const val CHANGE_PASSWORD_PATH = "$PROFILE_PATH/change-password"
        // End Profile

        fun findScreenByRoute(route: String?) = values().firstOrNull {
            it.route == route
        }
    }
}