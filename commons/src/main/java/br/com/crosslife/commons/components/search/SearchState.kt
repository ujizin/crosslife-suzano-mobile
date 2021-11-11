package br.com.crosslife.commons.components.search

import androidx.compose.runtime.*

sealed class SearchState {
    object Unfocused : SearchState()
    data class Focused(val text: String) : SearchState()

}

val State<SearchState>.currentText: String?
    get() = (value as? SearchState.Focused)?.text

@Composable
fun rememberSearchState(): MutableState<SearchState> = remember {
    mutableStateOf(SearchState.Unfocused)
}
