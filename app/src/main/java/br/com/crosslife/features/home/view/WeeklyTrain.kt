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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.crosslife.R
import br.com.crosslife.data.Result
import br.com.crosslife.domain.models.WeeklyTrain
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.ui.components.error.Error
import br.com.crosslife.ui.theme.Space
import br.com.crosslife.utils.DayOfWeek
import br.com.crosslife.utils.Saver
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@Composable
@ExperimentalPagerApi
fun WeeklyTrain(
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
        is Result.Error -> Error(onClick = { onRetryClick() })
        is Result.Success -> WeeklyTrainUI(state.data, onWeeklyTrainClick)
    }
}

@ExperimentalPagerApi
@Composable
private fun WeeklyTrainUI(
    weeklyTrains: List<WeeklyTrain>,
    onWeeklyTrainClick: OnWeeklyTrainClick,
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
        WeeklyTrainItem(
            Modifier
                .padding(start = Space.BORDER)
                .align(Alignment.CenterStart)
                .fillMaxWidth(0.8F)
                .defaultMinSize(minHeight = 200.dp)
                .aspectRatio(1.5F),
            weeklyTrain,
            onWeeklyTrainClick = onWeeklyTrainClick
        )
    }
}

@Composable
fun WeeklyTrainItem(
    modifier: Modifier = Modifier,
    weeklyTrain: WeeklyTrain,
    titleStyle: TextStyle = MaterialTheme.typography.h3,
    subTitleStyle: TextStyle = MaterialTheme.typography.body1,
    innerModifier: Modifier = Modifier.padding(start = Space.XXS, bottom = Space.XXS),
    onWeeklyTrainClick: OnWeeklyTrainClick,
) {
    Card(
        Modifier
            .then(modifier)
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
                .background(Color.Black.copy(0.5F))
                .then(innerModifier)
        ) {
            val dayOfWeekRes = DayOfWeek.getDay(weeklyTrain.dayWeek).stringRes
            Text(stringResource(dayOfWeekRes).capitalize(), style = titleStyle)
            Text(
                weeklyTrain.title,
                fontWeight = FontWeight.Bold,
                style = subTitleStyle,
            )
        }
    }
}

typealias OnWeeklyTrainClick = (WeeklyTrain) -> Unit
