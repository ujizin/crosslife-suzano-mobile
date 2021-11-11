package br.com.crosslife.commons.utils

import android.content.Context
import androidx.annotation.StringRes
import br.com.crosslife.commons.R
import java.util.*

enum class DayOfWeek(@StringRes val stringRes: Int) {
    Sunday(R.string.sunday),
    Monday(R.string.monday),
    Tuesday(R.string.tuesday),
    Wednesday(R.string.wednesday),
    Thursday(R.string.thursday),
    Friday(R.string.friday),
    Saturday(R.string.saturday);

    companion object {
        fun getCurrentDay(): Int = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1

        fun getDay(dayOfWeek: Int) = values().first { it.ordinal == dayOfWeek }

        fun getNameDay(context: Context) = context.getString(getDay(getCurrentDay()).stringRes)
    }
}
