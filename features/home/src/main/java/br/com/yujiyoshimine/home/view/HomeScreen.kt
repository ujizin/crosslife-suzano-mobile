package br.com.yujiyoshimine.home.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.ui.components.swiperefresh.SwipeRefresh
import br.com.yujiyoshimine.commons.components.notices.noticesItems
import br.com.yujiyoshimine.commons.components.search.SearchLazyColumn
import br.com.yujiyoshimine.commons.components.search.currentText
import br.com.yujiyoshimine.commons.components.search.rememberSearchState
import br.com.yujiyoshimine.commons.extensions.navigateToDetailItem
import br.com.yujiyoshimine.commons.extensions.rememberFlowWithLifecycle
import br.com.yujiyoshimine.commons.theme.Space
import br.com.yujiyoshimine.commons.utils.DayOfWeek
import br.com.yujiyoshimine.domain.model.Result
import br.com.yujiyoshimine.home.viewmodel.HomeViewModel
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
        SearchLazyColumn(
            searchState = searchFieldState,
            noticeResult = noticesState,
            onRetryClick = { viewModel.getNotices(searchFieldState.currentText) },
            onValueChanged = {},
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
                    navigateToDetailItem(weeklyTrain.toDetailItem(DayOfWeek.getNameDay(context)))
                }
            }
            noticesItems(
                state = noticesState,
                onRetryClick = {
                    viewModel.getNotices(searchFieldState.currentText)
                }
            ) { notice ->
                navigateToDetailItem(notice.toDetailItem())
            }
            item { Spacer(Modifier.height(Space.BORDER)) }
        }
    }
}
