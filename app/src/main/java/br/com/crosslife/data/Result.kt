package br.com.crosslife.data

import android.util.Log

sealed class Result<out R> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val error: br.com.crosslife.core.network.Error) : Result<Nothing>() {
        init {
            Log.e(ERROR_NETWORK_LOG, error.error.message.orEmpty())
        }
    }

    object Initial : Result<Nothing>()
    object Loading : Result<Nothing>()

    companion object {
        private const val ERROR_NETWORK_LOG = "Network Error Log"
    }
}