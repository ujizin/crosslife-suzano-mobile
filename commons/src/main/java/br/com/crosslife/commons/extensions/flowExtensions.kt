package br.com.crosslife.commons.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import br.com.crosslife.domain.model.Result
import br.com.crosslife.domain.model.ServerError
import br.com.crosslife.domain.model.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

private const val DEBOUNCE_DELAY = 300L

fun <T> Flow<T>.catchNetwork(block: (ServerError) -> Unit) = catch {
    block(it as? ServerError ?: ServerError(it, Status.UNKNOWN))
}

fun <T> Flow<T>.notify(
    scope: CoroutineScope,
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
    }.launchIn(scope)

fun <T> Flow<T>.onSentenceDelay(text: String?) = onStart {
    if (!text.isNullOrBlank()) delay(DEBOUNCE_DELAY)
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