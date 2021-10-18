package br.com.crosslife.features.home.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.features.detailscreen.navigateToDetailItem
import br.com.crosslife.features.search.viewmodel.SearchViewModel
import br.com.crosslife.ui.theme.Gray
import br.com.crosslife.ui.theme.Space

sealed class SearchState {
    object Unfocused : SearchState()
    data class Focused(val text: String) : SearchState()
}

@Composable
fun rememberSearchState(): MutableState<SearchState> = remember {
    mutableStateOf(SearchState.Unfocused)
}

@Composable
fun NavController.SearchLazyColumn(
    searchState: MutableState<SearchState>,
    header: LazyListScope.() -> Unit = {},
    searchViewModel: SearchViewModel = hiltViewModel(),
    content: LazyListScope.() -> Unit,
) {
    val noticeState by searchViewModel.notices.collectAsState()
    LazyColumn(Modifier.fillMaxWidth()) {
        header()
        item { SearchField(searchState = searchState, searchViewModel::getNotices)  }
        if (searchState.value == SearchState.Unfocused) {
            content()
        } else {
            noticesItems(noticeState) {
                navigateToDetailItem(it.toDetailItem())
            }
        }

    }
}

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
