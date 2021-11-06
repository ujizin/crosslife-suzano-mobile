package br.com.yujiyoshimine.network.utils

import br.com.yujiyoshimine.network.ServerError
import br.com.yujiyoshimine.network.Status
import retrofit2.HttpException

object NetworkUtils {

    private fun getStatus(status: Int): Status = Status.values().firstOrNull {
        it.code == status
    } ?: Status.UNKNOWN

    fun Throwable.toApiError(): ServerError {
        val code = when (this) {
            is HttpException -> code()
            else -> Status.NO_NETWORK.code
        }

        return ServerError(this, getStatus(code))
    }
}