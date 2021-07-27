package br.com.crosslife.features.home.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.Screen
import br.com.crosslife.data.Result
import br.com.crosslife.domain.models.WeeklyTrain
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.features.home.viewmodel.HomeViewModel
import br.com.crosslife.ui.theme.DarkGray
import br.com.crosslife.ui.theme.Gray
import br.com.crosslife.ui.theme.Space
import br.com.crosslife.utils.DayOfWeek
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun NavController.HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    LazyColumn(Modifier.fillMaxSize()) {
        item { Logo() }
        item { TextFieldSearch(viewModel) }
        item { WeeklyTrain(viewModel) }
        Notices()
        item { Spacer(Modifier.height(Space.BOTTOM_NAVIGATION_MARGIN)) }
    }
}

@Composable
fun TextFieldSearch(viewModel: HomeViewModel) {
    val searchState = remember { mutableStateOf("") }
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
        Result.Initial, Result.Loading -> Loading()
        is Result.Error -> Loading()
        is Result.Success -> WeeklyTrainComponent(state.data) { id ->
            navigate(Screen.WeeklyTrain.createRoute(id))
        }
    }
}

@ExperimentalPagerApi
@Composable
fun WeeklyTrainComponent(weeklyTrains: List<WeeklyTrain>, onWeeklyTrainClick: (Int) -> Unit) {
    val pagerState = rememberPagerState(
        pageCount = weeklyTrains.size,
        infiniteLoop = true,
        initialPage = DayOfWeek.getCurrentDay(),
        initialOffscreenLimit = 7,
    )
    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Space.XXS),
        horizontalAlignment = Alignment.Start,
        state = pagerState,
    ) { page ->
        val weeklyTrain = weeklyTrains[page]
        WeeklyTrainItem(weeklyTrain, onWeeklyTrainClick)
    }
}

@ExperimentalPagerApi
@Composable
fun PagerScope.WeeklyTrainItem(weeklyTrain: WeeklyTrain, onWeeklyTrainClick: (Int) -> Unit) {
    Card(
        Modifier
            .align(Alignment.CenterStart)
            .padding(start = Space.BORDER)
            .fillMaxWidth(0.8F)
            .defaultMinSize(minHeight = 200.dp)
            .aspectRatio(1.5F)
            .clip(MaterialTheme.shapes.large)
            .clickable(onClick = { onWeeklyTrainClick(weeklyTrain.id) }),
        backgroundColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.large,
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.padding(start = Space.XXS, bottom = Space.XXS)
        ) {
            val dayOfWeekRes = DayOfWeek.getDay(weeklyTrain.dayWeek).stringRes
            Text(stringResource(dayOfWeekRes).capitalize(), style = MaterialTheme.typography.h3)
            Text(
                weeklyTrain.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

fun LazyListScope.Notices() {
    item {
        Text(
            modifier = Modifier
                .padding(top = Space.XXXS)
                .padding(horizontal = Space.BORDER),
            text = stringResource(id = R.string.notices).capitalize(),
            style = MaterialTheme.typography.h3,
        )
    }
    items(10) {
        NoticeItem()
    }
}

@Composable
fun NoticeItem() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = Space.BORDER)
            .padding(top = Space.XS)
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.small)
            .clickable {

            }
            .padding(Space.S),
    ) {
        Box(
            Modifier
                .size(120.dp, 80.dp)
                .background(MaterialTheme.colors.surface, MaterialTheme.shapes.medium)
        )
        Column(
            Modifier
                .padding(start = Space.XXS)
                .height(80.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Título",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = "de: Lucas Yuji Yoshimine",
                style = MaterialTheme.typography.body2,
                color = DarkGray,
            )
            Text(
                text = "16/04/2021 - hoje",
                style = MaterialTheme.typography.body2,
                color = DarkGray,
            )
        }
    }
}

@Composable
private fun Logo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Space.BORDER),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .padding(top = Space.XXS)
                .size(56.dp, 52.dp),
            painter = painterResource(
                id = R.drawable.crosslife_logo),
            contentDescription = stringResource(id = R.string.app_logo),
        )
    }
}

@Composable
private fun Loading() {
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
