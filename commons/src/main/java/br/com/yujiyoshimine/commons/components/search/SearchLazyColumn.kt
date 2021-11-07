package br.com.yujiyoshimine.commons.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.yujiyoshimine.commons.components.notices.noticesItems
import br.com.yujiyoshimine.commons.extensions.navigateToDetailItem
import br.com.yujiyoshimine.domain.model.Notice
import br.com.yujiyoshimine.domain.model.Result

@Composable
fun NavController.SearchLazyColumn(
    searchState: MutableState<SearchState>,
    noticeResult: Result<List<Notice>>,
    onRetryClick: () -> Unit,
    onValueChanged: (String) -> Unit,
    header: LazyListScope.() -> Unit = {},
    content: LazyListScope.() -> Unit,
) {
    LazyColumn(Modifier.fillMaxWidth()) {
        header()
        item { SearchField(searchState = searchState, onValueChanged) }
        if (searchState.value == SearchState.Unfocused) {
            content()
        } else {
            noticesItems(
                state = noticeResult,
                onRetryClick = onRetryClick,
            ) {
                navigateToDetailItem(it.toDetailItem())
            }
        }

    }
}