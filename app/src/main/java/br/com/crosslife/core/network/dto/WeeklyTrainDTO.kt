package br.com.crosslife.core.network.dto

import com.squareup.moshi.Json

data class WeeklyTrainDTO(
    @field:Json(name = "id_treino")
    val id: Int,
    @field:Json(name = "titulo")
    val title: String,
    @field:Json(name = "dia_da_semana")
    val dayOfWeek: Int,
//    @field:Json(name = "")
//    val description: String,
    @field:Json(name = "imagem_url")
    val imageUrl: String,
)
