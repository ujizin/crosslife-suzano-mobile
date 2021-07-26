package br.com.crosslife.utils

import android.content.Context
import br.com.crosslife.R
import br.com.crosslife.core.network.Error
import br.com.crosslife.data.errors.EmptyError

object ErrorHelper {

    fun getMessageFromState(context: Context, error: Error) =
        when (error.error) {
            is EmptyError -> context.getString(R.string.empty_error_message)
            else -> context.getString(R.string.default_error_message)
        }
}
