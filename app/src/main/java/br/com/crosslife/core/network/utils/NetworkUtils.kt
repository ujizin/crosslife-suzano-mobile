package br.com.crosslife.core.network.utils

import br.com.crosslife.core.network.ServerError
import br.com.crosslife.core.network.Status
import retrofit2.HttpException

object NetworkUtils {

    private fun getStatus(status: Int): Status = Status.values().firstOrNull {
        it.code == status
    } ?: Status.Unknown

    fun Throwable.toApiError(): ServerError {
        val code = when (this) {
            is HttpException -> code()
            else -> Status.NoNetwork.code
        }

        return ServerError(this, getStatus(code))
    }
}