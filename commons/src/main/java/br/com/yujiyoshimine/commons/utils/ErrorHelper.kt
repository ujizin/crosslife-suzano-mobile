package br.com.yujiyoshimine.commons.utils

import android.content.Context
import br.com.yujiyoshimine.commons.R
import br.com.yujiyoshimine.domain.model.EmptyError
import br.com.yujiyoshimine.domain.model.PasswordNotEqualsError
import br.com.yujiyoshimine.domain.model.ServerError

object ErrorHelper {

    fun getMessageFromState(context: Context, serverError: ServerError) =
        when (serverError.error) {
            is EmptyError -> context.getString(R.string.empty_error_message)
            is PasswordNotEqualsError -> context.getString(R.string.password_not_equals)
            else -> context.getString(R.string.default_error_message)
        }
}
