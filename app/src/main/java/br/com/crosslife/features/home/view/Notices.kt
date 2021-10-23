package br.com.crosslife.features.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.crosslife.R
import br.com.crosslife.data.Result
import br.com.crosslife.domain.models.Notice
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.ui.theme.DarkGray
import br.com.crosslife.ui.theme.Space
import coil.compose.rememberImagePainter

fun LazyListScope.noticesItems(state: Result<List<Notice>>, onNoticeClick: OnNoticeClick) {
    item {
        Text(
            modifier = Modifier
                .padding(top = Space.XXXS)
                .padding(horizontal = Space.BORDER),
            text = stringResource(id = R.string.notices).capitalize(),
            style = MaterialTheme.typography.h3,
        )
    }

    when (state) {
        Result.Initial, Result.Loading -> item { HomeLoading() }
        is Result.Error -> item { HomeLoading() } // TODO handle error
        is Result.Success -> items(state.data) { notice -> NoticeItem(notice, onNoticeClick) }
    }
}

@Composable
fun NoticeItem(notice: Notice, onNoticeClick: OnNoticeClick) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = Space.BORDER)
            .padding(top = Space.XS)
            .clip(MaterialTheme.shapes.small)
            .clickable {
                onNoticeClick(notice)
            }
            .padding(Space.S),
    ) {
        Image(
            painter = rememberImagePainter(notice.imageUrl),
            contentDescription = notice.subtitle,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(125.dp)
                .fillMaxHeight()
                .background(MaterialTheme.colors.surface, MaterialTheme.shapes.medium)
                .clip(MaterialTheme.shapes.small)
        )
        Column(
            Modifier.padding(start = Space.XXS),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = notice.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2,
            )
            Text(
                text = stringResource(R.string.from_author, notice.author),
                style = MaterialTheme.typography.body2,
                color = DarkGray,
            )
            Text(
                text = notice.date,
                style = MaterialTheme.typography.body2,
                color = DarkGray,
            )
        }
    }
}

typealias OnNoticeClick = (Notice) -> Unit