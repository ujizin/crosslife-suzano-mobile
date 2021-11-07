package br.com.yujiyoshimine.commons.utils

import br.com.yujiyoshimine.domain.model.ServerError
import br.com.yujiyoshimine.domain.model.Status

object NetworkUtils {

    private fun getStatus(status: Int): Status = Status.values().firstOrNull {
        it.code == status
    } ?: Status.UNKNOWN

    fun Throwable.toApiError(): ServerError {
        val code = when (this) {
//            is HttpException -> code() // FIXME get http exception interface
            else -> Status.NO_NETWORK.code
        }

        return ServerError(this, getStatus(code))
    }
}