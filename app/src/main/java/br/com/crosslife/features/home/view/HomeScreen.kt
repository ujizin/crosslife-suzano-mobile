package br.com.crosslife.features.home.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.data.Result
import br.com.crosslife.extensions.rememberFlowWithLifecycle
import br.com.crosslife.features.detailscreen.navigateToDetailItem
import br.com.crosslife.features.home.viewmodel.HomeViewModel
import br.com.crosslife.ui.components.swiperefresh.SwipeRefresh
import br.com.crosslife.ui.theme.Space
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun NavController.HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val isRefreshing by rememberFlowWithLifecycle(viewModel.isRefresh)
        .collectAsState(initial = false)
    val weeklyTrainState by rememberFlowWithLifecycle(viewModel.weeklyTrains)
        .collectAsState(initial = Result.Initial)
    val noticesState by rememberFlowWithLifecycle(viewModel.notices)
        .collectAsState(initial = Result.Initial)
    val searchFieldState = rememberSearchState()
    SwipeRefresh(
        isRefreshing = isRefreshing,
        onRefresh = { viewModel.refresh() },
    ) {
        SearchLazyColumn(searchFieldState,
            header = {
                item { Logo(Modifier.padding(top = Space.XXXS)) }
            }
        ) {
            item {
                WeeklyTrain(
                    state = weeklyTrainState,
                    onRetryClick = {
                        viewModel.getWeeklyTrains()
                    },
                ) { weeklyTrain ->
                    navigateToDetailItem(weeklyTrain.toDetailItem(context))
                }
            }
            noticesItems(
                state = noticesState,
                onRetryClick = {
                    viewModel.getNotices()
                }
            ) { notice ->
                navigateToDetailItem(notice.toDetailItem())
            }
            item { Spacer(Modifier.height(Space.BORDER)) }
        }
    }
}
