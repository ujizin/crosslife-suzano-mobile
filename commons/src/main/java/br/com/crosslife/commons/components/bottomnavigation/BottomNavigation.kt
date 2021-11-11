package br.com.crosslife.commons.components.bottomnavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.crosslife.commons.extensions.capitalize
import br.com.crosslife.navigation.Screen
import br.com.crosslife.navigation.Tab

@Composable
fun BottomNavigation(currentTab: Tab, onTabItemClicked: (Screen) -> Unit) {
    androidx.compose.material.BottomNavigation(
        modifier = Modifier.background(MaterialTheme.colors.surface)
    ) {
        MenuItem.values().forEach { tab ->
            val isCurrentSelected = tab.name == currentTab.name
            BottomNavigationItem(
                modifier = Modifier.background(MaterialTheme.colors.surface),
                icon = {
                    val color = if (isCurrentSelected) MaterialTheme.colors.primary else Gray
                    Image(
                        painter = painterResource(id = tab.iconRes),
                        colorFilter = ColorFilter.tint(color),
                        contentDescription = stringResource(id = tab.titleRes),
                    )
                },
                alwaysShowLabel = false,
                selectedContentColor = Color.White,
                label = {
                    Text(
                        stringResource(id = tab.titleRes).capitalize(),
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.overline,
                    )
                },
                selected = isCurrentSelected,
                onClick = { onTabItemClicked(tab.screen) },
            )
        }
    }
}