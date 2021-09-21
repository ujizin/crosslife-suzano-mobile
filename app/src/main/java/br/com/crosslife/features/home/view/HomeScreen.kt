package br.com.crosslife.features.home.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.features.home.viewmodel.HomeViewModel
import br.com.crosslife.ui.components.swiperefresh.SwipeRefresh
import br.com.crosslife.ui.theme.Space
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun NavController.HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val isRefreshing by viewModel.isRefresh.collectAsState()
    val weeklyTrainState by viewModel.weeklyTrains.collectAsState()
    val noticesState by viewModel.notices.collectAsState()
    SwipeRefresh(
        isRefreshing = isRefreshing,
        onRefresh = { viewModel.refresh() },
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            item { HomeLogo() }
            item { SearchField(viewModel) }
            item { WeeklyTrain(weeklyTrainState) }
            noticesItems(noticesState)
            item { Spacer(Modifier.height(Space.BOTTOM_NAVIGATION_MARGIN)) }
        }
    }
}
