package br.com.crosslife.ui.components.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.crosslife.R
import br.com.crosslife.Screen

enum class MenuItem(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val screen: Screen,
) {
    Home(R.string.menu_item_home, R.drawable.ic_home, Screen.HomeRoot),
    Search(R.string.menu_item_search, R.drawable.ic_search, Screen.SearchRoot),
    Chat(R.string.menu_item_chat, R.drawable.ic_chat, Screen.ChatRoot),
    Profile(R.string.menu_item_profile, R.drawable.ic_user, Screen.ProfileRoot),
}

enum class Tab {
    None,
    Home,
    Search,
    Chat,
    Profile
}