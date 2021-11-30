package br.com.crosslife.commons.components.bottombox

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.crosslife.commons.theme.Space
import br.com.crosslife.commons.theme.White

@Composable
fun BottomBox(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = title,
                style = MaterialTheme.typography.h2,
                color = White,
                maxLines = 3,
                modifier = Modifier.padding(horizontal = Space.BORDER),
            )
            Card(
                Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 250.dp)
                    .padding(top = Space.XXS),
                shape = RoundedCornerShape(topStart = Space.XXXS, topEnd = Space.XXXS),
                backgroundColor = MaterialTheme.colors.background,
            ) {
                Column(
                    Modifier.padding(Space.BORDER),
                    verticalArrangement = Arrangement.Top,
                    content = content,
                )
            }
        }
    }
}
