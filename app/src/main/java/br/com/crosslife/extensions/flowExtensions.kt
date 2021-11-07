package br.com.crosslife.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import br.com.crosslife.core.network.ServerError
import br.com.crosslife.core.network.utils.NetworkUtils.toApiError
import br.com.crosslife.data.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.catchNetwork(block: (ServerError) -> Unit) = catch { block(it.toApiError()) }

fun <T> Flow<T>.notify(
    scope: CoroutineScope? = null,
    stateFlow: MutableStateFlow<Result<T>>,
    finallyBlock: () -> Unit = {},
) =
    onStart {
        stateFlow.value = Result.Loading
    }.catchNetwork {
        stateFlow.value = Result.Error(it)
        finallyBlock()
    }.onEach {
        stateFlow.value = Result.Success(it)
        finallyBlock()
    }.apply {
        val coroutineScope = scope ?: return@apply
        launchIn(coroutineScope)
    }

@Composable
fun <T> rememberFlowWithLifecycle(
    flow: Flow<T>,
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
): Flow<T> = remember(flow, lifecycle) {
    flow.flowWithLifecycle(
        lifecycle = lifecycle,
        minActiveState = minActiveState
    )
}