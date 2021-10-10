package br.com.crosslife.domain.models

data class Notice(
    val id: Int,
    val title: String,
    val subtitle: String?,
    val content: String,
    val category: String,
    val author: String,
    val date: String, // TODO add an adapter to Date
    val imageUrl: String,
) {

    fun toDetailItem() = DetailItem(
        title = title,
        subTitle = title,
        description = content,
        imageUrl = imageUrl
    )
}