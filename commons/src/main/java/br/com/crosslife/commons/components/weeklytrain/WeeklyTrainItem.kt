package br.com.crosslife.commons.components.weeklytrain

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import br.com.crosslife.commons.extensions.capitalize
import br.com.crosslife.commons.theme.Space
import br.com.crosslife.commons.theme.White
import br.com.crosslife.commons.utils.DayOfWeek
import br.com.crosslife.commons.utils.OnWeeklyTrainClick
import br.com.crosslife.domain.model.WeeklyTrain
import coil.compose.rememberImagePainter

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
            Text(stringResource(dayOfWeekRes).capitalize(), style = titleStyle, color = White)
            Text(
                weeklyTrain.title,
                fontWeight = FontWeight.Bold,
                color = White,
                style = subTitleStyle,
            )
        }
    }
}