package br.com.crosslife.commons.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.material.Button as MaterialButton

@Composable
fun Button(
    modifier: Modifier = Modifier,
    buttonStyle: Button = Button.Primary,
    isLoading: Boolean = false,
    textButton: String = "",
    onClick: () -> Unit,
    content: (@Composable RowScope.() -> Unit)? = null,
) {
    MaterialButton(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            disabledBackgroundColor = MaterialTheme.colors.primary,
        ),
        modifier = Modifier
            .defaultMinSize(minHeight = 40.dp)
            .then(modifier),
        onClick = onClick,
        enabled = isLoading.not(),
        content = {
            if (isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 4.dp)
                        .size(22.dp, 22.dp)
                )
            } else {
                content?.invoke(this) ?: run {
                    Text(
                        text = textButton,
                        modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                        textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    )
                }
            }
        }
    )
}
