package br.com.crosslife.commons.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.crosslife.commons.R
import br.com.crosslife.commons.extensions.capitalize
import br.com.crosslife.commons.theme.Gray
import br.com.crosslife.commons.theme.Space

@Composable
fun SearchField(
    searchState: MutableState<SearchState>,
    onValueChanged: (String) -> Unit,
) {
    val textState = remember { mutableStateOf("") }
    TextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
            onValueChanged(it)
            searchState.value = when {
                it != "" -> SearchState.Focused(it)
                else -> SearchState.Unfocused
            }
        },
        singleLine = true,
        modifier = Modifier
            .padding(top = Space.XXXS)
            .padding(horizontal = Space.BORDER)
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search).capitalize(),
                color = Gray,
                modifier = Modifier.alpha(0.4F),
                style = MaterialTheme.typography.body1
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            placeholderColor = Gray,
        ),
        shape = MaterialTheme.shapes.medium,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                tint = Gray,
                contentDescription = stringResource(id = R.string.search),
                modifier = Modifier.size(32.dp)
            )
        }
    )
}