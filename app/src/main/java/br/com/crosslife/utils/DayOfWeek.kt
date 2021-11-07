package br.com.crosslife.utils

import androidx.annotation.StringRes
import br.com.crosslife.R
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
    }

}
