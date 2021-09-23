package br.com.crosslife.core.network.services

import br.com.crosslife.core.network.dto.NoticeDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface NoticeService {
    @GET("/noticias")
    fun fetchNotices(): Flow<List<NoticeDTO>>
}
