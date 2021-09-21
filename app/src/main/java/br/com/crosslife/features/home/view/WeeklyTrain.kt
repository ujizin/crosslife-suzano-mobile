package br.com.crosslife.features.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.Screen
import br.com.crosslife.data.Result
import br.com.crosslife.domain.models.WeeklyTrain
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.extensions.navigate
import br.com.crosslife.ui.theme.Space
import br.com.crosslife.utils.DayOfWeek
import br.com.crosslife.utils.Saver
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.PagerState


@Composable
@ExperimentalPagerApi
fun NavController.WeeklyTrain(state: Result<List<WeeklyTrain>>) {
    Text(
        modifier = Modifier
            .padding(top = Space.XS)
            .padding(horizontal = Space.BORDER),
        text = stringResource(id = R.string.weekly_train).capitalize(),
        style = MaterialTheme.typography.h3,
    )

    when (state) {
        Result.Initial, Result.Loading -> HomeLoading()
        is Result.Error -> HomeLoading() // TODO handle error
        is Result.Success -> WeeklyTrainUI(state.data) { weeklyTrain ->
            currentBackStackEntry?.arguments?.putParcelable(
                "detail_item",
                weeklyTrain.toDetailItem(context)
            )
            navigate(Screen.WeeklyTrain)
        }
    }
}

@ExperimentalPagerApi
@Composable
private fun WeeklyTrainUI(
    weeklyTrains: List<WeeklyTrain>,
    onWeeklyTrainClick: (WeeklyTrain) -> Unit,
) {
    val pagerState = rememberSaveable(saver = Saver.getPagerState(7, true)) {
        PagerState(
            pageCount = weeklyTrains.size,
            infiniteLoop = true,
            currentPage = DayOfWeek.getCurrentDay(),
            offscreenLimit = 7,
        )
    }
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
fun PagerScope.WeeklyTrainItem(
    weeklyTrain: WeeklyTrain,
    onWeeklyTrainClick: (WeeklyTrain) -> Unit,
) {
    Card(
        Modifier
            .align(Alignment.CenterStart)
            .padding(start = Space.BORDER)
            .fillMaxWidth(0.8F)
            .defaultMinSize(minHeight = 200.dp)
            .aspectRatio(1.5F)
            .clip(MaterialTheme.shapes.large)
            .clickable(onClick = { onWeeklyTrainClick(weeklyTrain) }),
        backgroundColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.large,
    ) {
        Image(
            painter = rememberImagePainter(weeklyTrain.imageUrl),
            contentDescription = weeklyTrain.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(0.25F))
                .padding(start = Space.XXS, bottom = Space.XXS)
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