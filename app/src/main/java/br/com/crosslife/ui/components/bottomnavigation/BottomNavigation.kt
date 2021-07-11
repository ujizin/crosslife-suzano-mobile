package br.com.crosslife.ui.components.bottomnavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.crosslife.navigate
import br.com.crosslife.ui.theme.Gray

@Composable
fun BottomNavigation(navController: NavController) {
    androidx.compose.material.BottomNavigation(
        modifier = Modifier.background(MaterialTheme.colors.surface)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination
        Tab.values().forEach { tab ->
            val isCurrentSelected = currentRoute?.route == tab.route.path
            BottomNavigationItem(
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
                        stringResource(id = tab.titleRes).replaceFirstChar { it.uppercase() },
                        color = MaterialTheme.colors.primary,
                        style = MaterialTheme.typography.overline,
                    )
                },
                selected = isCurrentSelected,
                onClick = { navController.navigate(tab.route, true) },
            )
        }
    }
}