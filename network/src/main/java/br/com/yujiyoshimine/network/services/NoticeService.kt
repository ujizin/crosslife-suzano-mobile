package br.com.yujiyoshimine.network.services

import br.com.yujiyoshimine.network.dto.NoticeDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeService {

    @GET("/noticias")
    suspend fun fetchNotices(@Query("titulo") sentence: String?): List<NoticeDTO>
}
