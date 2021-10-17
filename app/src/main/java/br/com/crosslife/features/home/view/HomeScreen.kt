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
import br.com.crosslife.features.detailscreen.navigateToDetailItem
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
    val searchFieldState = rememberSearchState()
    SwipeRefresh(
        isRefreshing = isRefreshing,
        onRefresh = { viewModel.refresh() },
    ) {
        SearchLazyColumn(searchFieldState,
            header = {
                item { HomeLogo() }
            }
        ) {
            item {
                WeeklyTrain(weeklyTrainState) { weeklyTrain ->
                    navigateToDetailItem(weeklyTrain.toDetailItem(context))
                }
            }
            noticesItems(noticesState) { notice ->
                navigateToDetailItem(notice.toDetailItem())
            }
            item { Spacer(Modifier.height(Space.BORDER)) }
        }
    }
}
