package br.com.crosslife.data.repositories

import br.com.crosslife.domain.model.GenericError
import br.com.crosslife.domain.model.ServerError
import br.com.crosslife.domain.model.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
fun <T> networkFlow(@BuilderInference block: suspend FlowCollector<T>.() -> Unit): Flow<T> =
    flow(block).catch {
        val status = when (it) {
            is GenericError -> throw it
            is HttpException -> Status.getStatus(it.code())
            else -> Status.NO_NETWORK
        }
        throw ServerError(it, status)
    }