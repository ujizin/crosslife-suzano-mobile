package br.com.crosslife.features.home.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.Screen
import br.com.crosslife.data.Result
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.features.home.viewmodel.HomeViewModel
import br.com.crosslife.navigate
import br.com.crosslife.ui.theme.Gray
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
            item { TextFieldSearch(viewModel) }
            item { WeeklyTrain(viewModel) }
            Notices()
            item { Spacer(Modifier.height(Space.BOTTOM_NAVIGATION_MARGIN)) }
        }
    }
}

@Composable
fun TextFieldSearch(viewModel: HomeViewModel) {
    val searchState = rememberSaveable { mutableStateOf("") }
    TextField(
        value = searchState.value,
        onValueChange = {
            searchState.value = it
        },
        singleLine = true,
        modifier = Modifier
            .padding(top = Space.XXXS)
            .padding(horizontal = Space.BORDER)
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search).capitalize(),
                color = Gray,
                modifier = Modifier.alpha(0.4F),
                style = MaterialTheme.typography.body1
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            placeholderColor = Gray,
        ),
        shape = MaterialTheme.shapes.medium,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.search),
                modifier = Modifier.size(32.dp)
            )
        }
    )
}

@Composable
@ExperimentalPagerApi
private fun NavController.WeeklyTrain(viewModel: HomeViewModel) {
    val weeklyTrains by viewModel.weeklyTrains.collectAsState()
    Text(
        modifier = Modifier
            .padding(top = Space.XS)
            .padding(horizontal = Space.BORDER),
        text = stringResource(id = R.string.weekly_train).capitalize(),
        style = MaterialTheme.typography.h3,
    )
    when (val state = weeklyTrains) {
        Result.Initial, Result.Loading -> HomeLoading()
        is Result.Error -> HomeLoading()
        is Result.Success -> WeeklyTrainComponent(state.data) { weeklyTrain ->
            currentBackStackEntry?.arguments?.putParcelable(
                "detail_item",
                weeklyTrain.toDetailItem(context)
            )
            navigate(Screen.WeeklyTrain)
        }
    }
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
