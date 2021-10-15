package br.com.crosslife.features.search.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.data.Result
import br.com.crosslife.domain.models.WeeklyTrain
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.features.detailscreen.navigateToDetailItem
import br.com.crosslife.features.home.view.*
import br.com.crosslife.features.search.viewmodel.SearchViewModel
import br.com.crosslife.ui.theme.Space
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode

@ExperimentalFoundationApi
@Composable
fun NavController.SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    val weeklyTrainState by viewModel.weeklyTrains.collectAsState()
    val noticeState by viewModel.notices.collectAsState()

    LazyColumn {
        item { SearchField() }
        item {
            SearchWeeklyTrain(weeklyTrainState) {
                navigateToDetailItem(it.toDetailItem(context))
            }
        }
        noticesItems(noticeState) {
            navigateToDetailItem(it.toDetailItem())
        }
        item { Spacer(Modifier.height(Space.BORDER)) }
    }
}

@Composable
private fun SearchWeeklyTrain(
    state: Result<List<WeeklyTrain>>,
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
        Result.Initial, Result.Loading -> {}
        is Result.Error -> {}
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
                WeeklyTrainItem(
                    modifier = Modifier
                        .fillMaxWidth(if (item.isFirstDayOfTheWeek) 1F else 0.485F)
                        .aspectRatio(2F),
                    weeklyTrain = item,
                    onWeeklyTrainClick = onWeeklyTrainClick
                )
            }
        }
    }
}