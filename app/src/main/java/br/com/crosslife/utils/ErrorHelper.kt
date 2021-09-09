package br.com.crosslife.utils

import android.content.Context
import br.com.crosslife.R
import br.com.crosslife.core.network.Error
import br.com.crosslife.data.errors.EmptyError
import br.com.crosslife.data.errors.PasswordNotEqualsError

object ErrorHelper {

    fun getMessageFromState(context: Context, error: Error) =
        when (error.error) {
            is EmptyError -> context.getString(R.string.empty_error_message)
            is PasswordNotEqualsError -> context.getString(R.string.password_not_equals)
            else -> context.getString(R.string.default_error_message)
        }
}
