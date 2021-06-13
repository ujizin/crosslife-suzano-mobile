package br.com.crosslife.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material.Button as MaterialButton

@Composable
fun Button(
    modifier: Modifier = Modifier,
    buttonStyle: Style.Button = Style.Button.Primary,
    textButton: String = "",
    onClick: () -> Unit,
    content: (@Composable RowScope.() -> Unit)? = null,
) {
    MaterialButton(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        ),
        modifier = modifier,
        onClick = onClick,
        content = content ?: {
            Text(
                text = textButton,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                textAlign = TextAlign.Center,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
            )
        },
    )
}
