package br.com.crosslife.network.dto

import com.squareup.moshi.Json

data class NoticeDTO(
    val id: Int,
    @field:Json(name = "titulo")
    val title: String,
    @field:Json(name = "subtitulo")
    val subtitle: String?,
    @field:Json(name = "corpo")
    val content: String,
    @field:Json(name = "categoria")
    val category: String,
    @field:Json(name = "autor")
    val author: String,
    @field:Json(name = "data_criacao")
    val date: String, // TODO add an adapter to Date
    @field:Json(name = "capa_url")
    val imageUrl: String,
)