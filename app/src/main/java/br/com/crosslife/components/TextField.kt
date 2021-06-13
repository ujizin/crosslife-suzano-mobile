package br.com.crosslife.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun TextField(
    label: String,
    state: MutableState<String>,
    modifier: Modifier = Modifier,
    modifierInput: Modifier = Modifier,
    valueChanged: ((String) -> Unit)? = null
) {
    Column(modifier = modifier) {
        Text(text = label)
        BasicTextField(
            value = state.value,
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .border(
                    BorderStroke(1.dp, MaterialTheme.colors.onPrimary),
                    RoundedCornerShape(3.dp)
                )
                .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
                .then(modifierInput),
            onValueChange = {
                state.value = it
                valueChanged?.invoke(it)
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.onPrimary,
                fontSize = MaterialTheme.typography.body1.fontSize,
            ),
            cursorBrush = SolidColor(MaterialTheme.colors.primary)
        )
    }
}
