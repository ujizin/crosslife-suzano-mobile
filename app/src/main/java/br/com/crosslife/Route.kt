package br.com.crosslife

import br.com.crosslife.ui.components.bottomnavigation.Tab

sealed class Route(val path: String, val tabItem: Tab = Tab.Home) {
    object Splash : Route(SPLASH_PATH, Tab.None)
    object Login : Route(LOGIN_PATH, Tab.None)
    object Home : Route(HOME_PATH, Tab.Home)
    object Search : Route(SEARCH_PATH, Tab.Search)
    object Chat : Route(CHAT_PATH, Tab.Chat)
    object Profile : Route(PROFILE_PATH, Tab.Profile)
    object DetailProfile : Route(DETAIL_PROFILE_PATH, Tab.Profile)
    object ChangePassword : Route(CHANGE_PASSWORD_PATH, Tab.Profile)

    companion object {
        private const val SPLASH_PATH = "splash"
        private const val LOGIN_PATH = "login"

        // Tabs
        private const val HOME_PATH = "home"
        private const val SEARCH_PATH = "search"
        private const val CHAT_PATH = "chat"
        private const val PROFILE_PATH = "profile"
        // End Tabs

        // Profile
        private const val DETAIL_PROFILE_PATH = "$PROFILE_PATH/student"
        private const val CHANGE_PASSWORD_PATH = "$PROFILE_PATH/change-password"
        // End Profile
    }
}