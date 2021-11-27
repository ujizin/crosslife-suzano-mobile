package br.com.crosslife.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailItem(
    val title: String,
    val subTitle: String? = null,
    val description: String,
    val imageUrl: String,
    val videoUrl: String? = null,
): Parcelable {

    companion object {
        const val DETAIL_ITEM_KEY = "detail_item"
    }
}
