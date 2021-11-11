package br.com.crosslife.network.dto

import com.squareup.moshi.Json

data class WeeklyTrainDTO(
    @field:Json(name = "id_treino")
    val id: Int,
    @field:Json(name = "titulo")
    val title: String,
    @field:Json(name = "dia_da_semana")
    val dayOfWeek: Int,
    @field:Json(name = "descricao")
    val description: String,
    @field:Json(name = "imagem_url")
    val imageUrl: String,
    @field:Json(name = "video_url")
    val videoUrl: String,
)
