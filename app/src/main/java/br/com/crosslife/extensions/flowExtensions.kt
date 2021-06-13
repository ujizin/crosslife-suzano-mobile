package br.com.crosslife.extensions

import br.com.crosslife.core.network.Error
import br.com.crosslife.core.network.utils.NetworkUtils.toApiError
import br.com.crosslife.data.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

fun <T> Flow<T>.catchNetwork(block: (Error) -> Unit) = catch { block(it.toApiError()) }

fun <T> Flow<T>.notify(scope: CoroutineScope, stateFlow: MutableStateFlow<Result<T>>) =
    onStart {
        stateFlow.value = Result.Loading
    }.catchNetwork {
        stateFlow.value = Result.Error(it)
    }.onEach {
        stateFlow.value = Result.Success(it)
    }.launchIn(scope)