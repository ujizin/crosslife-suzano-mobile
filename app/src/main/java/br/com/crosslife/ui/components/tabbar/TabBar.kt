package br.com.crosslife.ui.components.tabbar

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.crosslife.ui.components.bottomnavigation.BottomNavigation

@Composable
fun NavController.TabBar(content: @Composable () -> Unit) {
    Scaffold(
        bottomBar = { BottomNavigation(this) },
        content = { content() }
    )
}