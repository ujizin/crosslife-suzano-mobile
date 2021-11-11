package br.com.crosslife.domain.model

import android.util.Log

sealed class Result<out R> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val serverError: ServerError) : Result<Nothing>() {
        init {
            Log.e(ERROR_NETWORK_LOG, serverError.error.message.orEmpty())
        }
    }

    object Initial : Result<Nothing>()
    object Loading : Result<Nothing>()

    companion object {
        private const val ERROR_NETWORK_LOG = "Network Error Log"
    }
}