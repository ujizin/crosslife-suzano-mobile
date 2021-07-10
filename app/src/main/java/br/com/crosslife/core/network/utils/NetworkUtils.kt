package br.com.crosslife.core.network.utils

import br.com.crosslife.core.network.Error
import br.com.crosslife.core.network.Status
import retrofit2.HttpException

object NetworkUtils {

    private fun getStatus(status: Int): Status = Status.values().firstOrNull {
        it.code == status
    } ?: Status.Unknown

    fun Throwable.toApiError(): Error {
        val code = when (this) {
            is HttpException -> code()
            else -> Status.NoNetwork.code
        }

        return Error(this, getStatus(code))
    }
}