package br.com.yujiyoshimine.commons.components.notices

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.yujiyoshimine.commons.R
import br.com.yujiyoshimine.commons.components.Loading
import br.com.yujiyoshimine.commons.components.error.Error
import br.com.yujiyoshimine.commons.extensions.capitalize
import br.com.yujiyoshimine.commons.theme.DarkGray
import br.com.yujiyoshimine.commons.theme.Space
import br.com.yujiyoshimine.commons.utils.OnNoticeClick
import br.com.yujiyoshimine.domain.model.Notice
import br.com.yujiyoshimine.domain.model.Result
import coil.compose.rememberImagePainter

fun LazyListScope.noticesItems(
    state: Result<List<Notice>>,
    onRetryClick: () -> Unit,
    onNoticeClick: OnNoticeClick,
) {
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
        Result.Initial, Result.Loading -> item { Loading() }
        is Result.Error -> item { Error { onRetryClick() } }
        is Result.Success -> if (state.data.isNotEmpty()) {
            items(state.data) { notice -> NoticeItem(notice, onNoticeClick) }
        } else {
            item {
                Text(
                    text = stringResource(id = R.string.empty_notices),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Space.XXXS)
                )
            }
        }
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
