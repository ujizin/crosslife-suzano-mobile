package br.com.yujiyoshimine.home.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.yujiyoshimine.commons.extensions.rememberFlowWithLifecycle
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
//    val searchFieldState = rememberSearchState()
//    SwipeRefresh(
//        isRefreshing = isRefreshing,
//        onRefresh = { viewModel.refresh() },
//    ) {
//        SearchLazyColumn(searchFieldState,
//            header = {
//                item { Logo(Modifier.padding(top = Space.XXXS)) }
//            }
//        ) {
//            item {
//                WeeklyTrain(
//                    state = weeklyTrainState,
//                    onRetryClick = {
//                        viewModel.getWeeklyTrains()
//                    },
//                ) { weeklyTrain ->
//                    navigateToDetailItem(weeklyTrain.toDetailItem(DayOfWeek.getNameDay(context)))
//                }
//            }
//            noticesItems(
//                state = noticesState,
//                onRetryClick = {
//                    viewModel.getNotices()
//                }
//            ) { notice ->
//                navigateToDetailItem(notice.toDetailItem())
//            }
//            item { Spacer(Modifier.height(Space.BORDER)) }
//        }
//    }
}
