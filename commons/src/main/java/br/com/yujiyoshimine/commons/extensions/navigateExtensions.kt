package br.com.yujiyoshimine.commons.extensions

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import br.com.yujiyoshimine.domain.model.DetailItem
import br.com.yujiyoshimine.navigation.Screen

fun NavController.navigate(
    screen: Screen,
    newTask: Boolean = false,
) {
    navigate(screen.route) {
        if (newTask) {
            currentDestination?.route?.let { destinationRoute ->
                popUpTo(destinationRoute) { inclusive = true }
            }
        }
    }
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

fun NavController.navigateToDetailItem(detailItem: DetailItem) {
    currentBackStackEntry?.arguments?.putParcelable("detail_item", detailItem)
    navigate(Screen.DetailItem)
}