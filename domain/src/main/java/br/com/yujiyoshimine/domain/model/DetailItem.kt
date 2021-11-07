package br.com.yujiyoshimine.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailItem(
    val title: String,
    val subTitle: String? = null,
    val description: String,
    val imageUrl: String,
    val videoUrl: String? = null,
): Parcelable
