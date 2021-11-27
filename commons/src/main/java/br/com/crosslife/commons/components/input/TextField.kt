package br.com.crosslife.commons.components.input

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    label: String? = null,
    state: MutableState<String>,
    modifierInput: Modifier = Modifier,
    isPassword: Boolean = false,
    valueChanged: ((String) -> Unit)? = null,
) {
    val isPasswordVisible = remember { mutableStateOf(isPassword) }
    val image = getPasswordIcon(isPasswordVisible.value)
    Column(modifier = modifier) {
        label?.let {
            Text(text = label)
        }
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
            visualTransformation = if (isPasswordVisible.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            decorationBox = { innerTextField ->
                Row {
                    Box(
                        modifier = Modifier
                            .weight(1F)
                            .padding(end = 8.dp),
                    ) {
                        innerTextField()
                    }
                    if (state.value.isNotEmpty() && isPassword) {
                        IconButton(
                            { isPasswordVisible.value = isPasswordVisible.value.not() },
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(20.dp, 20.dp)
                        ) {
                            Icon(
                                imageVector = image,
                                contentDescription = "password",
                                tint = MaterialTheme.colors.onPrimary,
                            )
                        }
                    }
                }
            },
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.colors.primary)
        )
    }
}

fun getPasswordIcon(showPassword: Boolean): ImageVector {
    return if (showPassword) {
        Icons.Filled.Visibility
    } else {
        Icons.Filled.VisibilityOff
    }
}