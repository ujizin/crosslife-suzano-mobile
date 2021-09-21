package br.com.crosslife.features.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.crosslife.ui.theme.DarkGray
import br.com.crosslife.ui.theme.Space
import br.com.crosslife.R
import br.com.crosslife.data.Result
import br.com.crosslife.domain.models.WeeklyTrain
import br.com.crosslife.extensions.capitalize

fun LazyListScope.noticesItems(state: Result<WeeklyTrain>) {
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