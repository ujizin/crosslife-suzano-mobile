package br.com.crosslife.domain.model

data class WeeklyTrain(
    val id: Int,
    val title: String,
    val dayWeek: Int,
    val description: String,
    val imageUrl: String,
    val videoUrl: String,
) {

    val isFirstDayOfTheWeek = dayWeek == 0

    fun toDetailItem(dayOfTheWeek: String = "") = DetailItem(
        title = dayOfTheWeek,
        subTitle = title,
        description = description,
        imageUrl = imageUrl,
        videoUrl = videoUrl
    )
}
