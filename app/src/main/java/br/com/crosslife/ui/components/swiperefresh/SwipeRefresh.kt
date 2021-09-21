package br.com.crosslife.ui.components.swiperefresh

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun SwipeRefresh(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit,
) {
    com.google.accompanist.swiperefresh.SwipeRefresh(
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state, trigger,
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = MaterialTheme.colors.onPrimary
            )
        },
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = onRefresh,
        content = content
    )
}