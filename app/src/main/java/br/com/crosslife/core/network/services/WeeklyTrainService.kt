package br.com.crosslife.core.network.services

import br.com.crosslife.core.network.dto.WeeklyTrainDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface WeeklyTrainService {

    @GET("/treinos")
    suspend fun fetchWeeklyTrains(): List<WeeklyTrainDTO>

    @GET("/treinos/{id}")
    suspend fun fetchWeeklyTrain(@Path("id") id: Int): WeeklyTrainDTO
}
