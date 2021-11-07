package br.com.yujiyoshimine.search.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.yujiyoshimine.commons.R
import br.com.yujiyoshimine.commons.components.Loading
import br.com.yujiyoshimine.commons.components.error.Error
import br.com.yujiyoshimine.commons.components.notices.noticesItems
import br.com.yujiyoshimine.commons.components.weeklytrain.WeeklyTrainItem
import br.com.yujiyoshimine.commons.extensions.capitalize
import br.com.yujiyoshimine.commons.extensions.navigateToDetailItem
import br.com.yujiyoshimine.commons.extensions.rememberFlowWithLifecycle
import br.com.yujiyoshimine.commons.theme.Gray
import br.com.yujiyoshimine.commons.theme.Space
import br.com.yujiyoshimine.commons.utils.DayOfWeek
import br.com.yujiyoshimine.commons.utils.OnWeeklyTrainClick
import br.com.yujiyoshimine.domain.model.Result
import br.com.yujiyoshimine.domain.model.WeeklyTrain
import br.com.yujiyoshimine.search.viewmodel.SearchViewModel
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
    SearchLazyColumn(searchFieldState) {
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

sealed class SearchState {
    object Unfocused : SearchState()
    data class Focused(val text: String) : SearchState()
}

@Composable
fun rememberSearchState(): MutableState<SearchState> = remember {
    mutableStateOf(SearchState.Unfocused)
}

@Composable
fun NavController.SearchLazyColumn(
    searchState: MutableState<SearchState>,
    header: LazyListScope.() -> Unit = {},
    searchViewModel: SearchViewModel = hiltViewModel(),
    content: LazyListScope.() -> Unit,
) {
    val noticeState by rememberFlowWithLifecycle(searchViewModel.notices)
        .collectAsState(initial = Result.Initial)
    LazyColumn(Modifier.fillMaxWidth()) {
        header()
        item { SearchField(searchState = searchState, searchViewModel::getNotices) }
        if (searchState.value == SearchState.Unfocused) {
            content()
        } else {
            noticesItems(
                state = noticeState,
                onRetryClick = {
                    val sentence = (searchState.value as? SearchState.Focused)?.text
                    searchViewModel.getNotices(sentence)
                }
            ) {
                navigateToDetailItem(it.toDetailItem())
            }
        }

    }
}

@Composable
fun SearchField(
    searchState: MutableState<SearchState>,
    onValueChanged: (String) -> Unit,
) {
    val textState = remember { mutableStateOf("") }
    TextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
            onValueChanged(it)
            searchState.value = when {
                it != "" -> SearchState.Focused(it)
                else -> SearchState.Unfocused
            }
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
                tint = Gray,
                contentDescription = stringResource(id = R.string.search),
                modifier = Modifier.size(32.dp)
            )
        }
    )
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