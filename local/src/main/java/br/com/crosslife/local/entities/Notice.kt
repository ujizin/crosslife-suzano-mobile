package br.com.crosslife.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notice(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "sub_title")
    val subtitle: String?,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
)
