package br.com.crosslife.ui.components.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.crosslife.R
import br.com.crosslife.Route

enum class Tab(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val route: Route,
) {
    Home(R.string.menu_item_home, R.drawable.ic_home, Route.Home),
    Search(R.string.menu_item_search, R.drawable.ic_search, Route.Search),
    Chat(R.string.menu_item_chat, R.drawable.ic_chat, Route.Chat),
    Profile(R.string.menu_item_profile, R.drawable.ic_user, Route.Profile),
}