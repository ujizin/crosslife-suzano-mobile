package br.com.crosslife.features.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.Screen
import br.com.crosslife.data.Result
import br.com.crosslife.domain.models.WeeklyTrain
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.extensions.navigate
import br.com.crosslife.features.home.viewmodel.HomeViewModel
import br.com.crosslife.ui.theme.Space
import com.google.accompanist.pager.*
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalPagerApi
@Composable
fun NavController.HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val isRefreshing by viewModel.isRefresh.collectAsState()
    SwipeRefresh(
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state, trigger,
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
        },
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh() },
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            item { HomeLogo() }
            item { SearchField(viewModel) }
            item { WeeklyTrain(viewModel) }
            Notices()
            item { Spacer(Modifier.height(Space.BOTTOM_NAVIGATION_MARGIN)) }
        }
    }
}

@Composable
@ExperimentalPagerApi
private fun NavController.WeeklyTrain(viewModel: HomeViewModel) {
    val weeklyTrainState by viewModel.weeklyTrains.collectAsState()

    Text(
        modifier = Modifier
            .padding(top = Space.XS)
            .padding(horizontal = Space.BORDER),
        text = stringResource(id = R.string.weekly_train).capitalize(),
        style = MaterialTheme.typography.h3,
    )

    RenderWeeklyState(weeklyTrainState) { weeklyTrain ->
        currentBackStackEntry?.arguments?.putParcelable(
            "detail_item",
            weeklyTrain.toDetailItem(context)
        )
        navigate(Screen.WeeklyTrain)
    }
}

@ExperimentalPagerApi
@Composable
fun RenderWeeklyState(
    state: Result<List<WeeklyTrain>>,
    onWeeklyClick: (WeeklyTrain) -> Unit,
) = when (state) {
    Result.Initial, Result.Loading -> HomeLoading()
    is Result.Error -> HomeLoading() // TODO handle error
    is Result.Success -> WeeklyTrainComponent(state.data, onWeeklyClick)
}

@Composable
private fun HomeLoading() {
    Box(
        Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 200.dp)
            .aspectRatio(1.75F),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
