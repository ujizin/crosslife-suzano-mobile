package br.com.crosslife.network.services

import br.com.crosslife.network.dto.WeeklyTrainDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface WeeklyTrainService {

    @GET("/treinos")
    suspend fun fetchWeeklyTrains(): List<WeeklyTrainDTO>

    @GET("/treinos/{id}")
    suspend fun fetchWeeklyTrain(@Path("id") id: Int): WeeklyTrainDTO
}
