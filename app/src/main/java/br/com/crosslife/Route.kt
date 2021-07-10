package br.com.crosslife

sealed class Route(val path: String) {
    object Splash : Route(SPLASH_PATH)
    object Login : Route(LOGIN_PATH)
    object Home : Route(HOME_PATH)
    object Search: Route(SEARCH_PATH)
    object Chat: Route(CHAT_PATH)
    object Profile: Route(PROFILE_PATH)

    companion object {
        private const val SPLASH_PATH = "splash"
        private const val LOGIN_PATH = "login"

        private const val HOME_PATH = "home"
        private const val SEARCH_PATH = "search"
        private const val CHAT_PATH = "chat"
        private const val PROFILE_PATH = "profile"
    }
}