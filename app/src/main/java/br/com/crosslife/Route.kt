package br.com.crosslife

sealed class Route(val path: String) {
    object Splash : Route(SPLASH_PATH)
    object Login : Route(LOGIN_PATH)
    object Home : Route(HOME_PATH)

    companion object {
        private const val SPLASH_PATH = "splash"
        private const val LOGIN_PATH = "login"
        private const val HOME_PATH = "home"
    }
}