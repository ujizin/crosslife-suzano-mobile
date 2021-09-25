package br.com.crosslife.ui.components.bottombox

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.crosslife.ui.theme.Space

@Composable
fun BottomBox(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize().then(modifier),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Column(Modifier.fillMaxWidth()) {
            Text(
                text = title,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(horizontal = Space.BORDER),
            )
            Card(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = Space.XXS),
                shape = RoundedCornerShape(topStart = Space.XXXS, topEnd = Space.XXXS),
                backgroundColor = MaterialTheme.colors.background,
            ) {
                Column(
                    Modifier
                        .padding(Space.BORDER)
                        .wrapContentHeight(),
                    content = content,
                )
            }
        }
    }
}
