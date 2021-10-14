package br.com.crosslife.domain.models

import android.content.Context
import br.com.crosslife.utils.DayOfWeek

data class WeeklyTrain(
    val id: Int,
    val title: String,
    val dayWeek: Int,
    val description: String,
    val imageUrl: String,
    val videoUrl: String,
) {

    fun toDetailItem(context: Context) = DetailItem(
        title = context.getString(DayOfWeek.getDay(dayWeek).stringRes),
        subTitle = title,
        description = description,
        imageUrl = imageUrl,
        videoUrl = videoUrl
    )
}
