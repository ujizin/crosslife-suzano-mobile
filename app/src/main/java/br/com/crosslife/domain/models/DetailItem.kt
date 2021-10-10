package br.com.crosslife.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailItem(
    val title: String,
    val subTitle: String? = null,
    val description: String,
    val imageUrl: String,
): Parcelable
