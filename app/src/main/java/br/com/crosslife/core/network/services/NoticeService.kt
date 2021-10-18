package br.com.crosslife.core.network.services

import br.com.crosslife.core.network.dto.NoticeDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeService {
    @GET("/noticias")
    suspend fun fetchNotices(): List<NoticeDTO>

    @GET("/noticias")
    suspend fun fetchNotices(@Query("titulo") sentence: String): List<NoticeDTO>
}
