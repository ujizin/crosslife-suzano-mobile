package br.com.crosslife.search.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.commons.R
import br.com.crosslife.commons.components.Loading
import br.com.crosslife.commons.components.error.Error
import br.com.crosslife.commons.components.notices.noticesItems
import br.com.crosslife.commons.components.search.SearchLazyColumn
import br.com.crosslife.commons.components.search.currentText
import br.com.crosslife.commons.components.search.rememberSearchState
import br.com.crosslife.commons.components.weeklytrain.WeeklyTrainItem
import br.com.crosslife.commons.extensions.capitalize
import br.com.crosslife.commons.extensions.navigateToDetailItem
import br.com.crosslife.commons.extensions.rememberFlowWithLifecycle
import br.com.crosslife.commons.theme.Space
import br.com.crosslife.commons.utils.DayOfWeek
import br.com.crosslife.commons.utils.OnWeeklyTrainClick
import br.com.crosslife.domain.model.Result
import br.com.crosslife.domain.model.WeeklyTrain
import br.com.crosslife.search.viewmodel.SearchViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode

@ExperimentalFoundationApi
@Composable
fun NavController.SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val weeklyTrainState by rememberFlowWithLifecycle(viewModel.weeklyTrains)
        .collectAsState(initial = Result.Initial)
    val noticeState by rememberFlowWithLifecycle(viewModel.notices)
        .collectAsState(initial = Result.Initial)
    val searchFieldState = rememberSearchState()
    SearchLazyColumn(
        searchState = searchFieldState,
        noticeResult = noticeState,
        onValueChanged = {
            viewModel.getNotices(it)
        },
        onRetryClick = {
            viewModel.getNotices(searchFieldState.currentText)
        },
    ) {
        item {
            SearchWeeklyTrain(
                state = weeklyTrainState,
                onRetryClick = {
                    viewModel.getWeeklyTrains()
                }
            ) {
                val dayOfTheWeek = DayOfWeek.getNameDay(context)
                navigateToDetailItem(it.toDetailItem(dayOfTheWeek))
            }
        }
        noticesItems(
            state = noticeState,
            onRetryClick = { viewModel.getNotices() }
        ) {
            navigateToDetailItem(it.toDetailItem())
        }
        item { Spacer(Modifier.height(Space.BORDER)) }
    }
}


@Composable
private fun SearchWeeklyTrain(
    state: Result<List<WeeklyTrain>>,
    onRetryClick: () -> Unit,
    onWeeklyTrainClick: OnWeeklyTrainClick,
) {
    Text(
        modifier = Modifier
            .padding(top = Space.XS)
            .padding(horizontal = Space.BORDER),
        text = stringResource(id = R.string.weekly_train).capitalize(),
        style = MaterialTheme.typography.h3,
    )

    when (state) {
        Result.Initial, Result.Loading -> Loading()
        is Result.Error -> Error { onRetryClick() }
        is Result.Success -> WeeklyTrainUI(
            Modifier.padding(top = Space.XXS),
            state.data,
            onWeeklyTrainClick
        )
    }
}

@Composable
private fun WeeklyTrainUI(
    modifier: Modifier,
    weeklyTrains: List<WeeklyTrain>,
    onWeeklyTrainClick: OnWeeklyTrainClick,
) {
    Box(Modifier
        .padding(horizontal = Space.BORDER)
        .then(modifier)) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            mainAxisSize = SizeMode.Expand,
            mainAxisAlignment = MainAxisAlignment.SpaceBetween,
            crossAxisSpacing = Space.XS,
        ) {
            weeklyTrains.forEach { item ->
                val (width, titleStyle, subTitleStyle) = with(MaterialTheme.typography) {
                    when (item.isFirstDayOfTheWeek) {
                        true -> Triple(1F, h3, body1)
                        false -> Triple(0.485F, body1.copy(fontWeight = Bold), body2)
                    }
                }
                WeeklyTrainItem(
                    modifier = Modifier
                        .fillMaxWidth(width)
                        .aspectRatio(2F),
                    weeklyTrain = item,
                    innerModifier = Modifier.padding(start = Space.XS, bottom = Space.XS),
                    titleStyle = titleStyle,
                    subTitleStyle = subTitleStyle,
                    onWeeklyTrainClick = onWeeklyTrainClick
                )
            }
        }
    }
}