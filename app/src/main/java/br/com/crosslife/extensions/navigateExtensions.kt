package br.com.crosslife.extensions

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import br.com.crosslife.Screen

fun NavController.navigate(
    screen: Screen,
) {
    navigate(screen.route)
}

fun NavController.navigate(
    screen: Screen,
    block: NavOptionsBuilder.() -> Unit,
) {
    navigate(screen.route, block)
}

fun NavController.navigateAndPop(
    screen: Screen,
    popUpUntil: Screen,
    isStartDestination: Boolean = false,
) {
    navigate(screen.route) {
        popUpTo(popUpUntil.route) { inclusive = true }
        if (isStartDestination) {
            graph.setStartDestination(screen.route)
        }
    }
}