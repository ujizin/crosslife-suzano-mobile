package br.com.yujiyoshimine.network.services

import br.com.yujiyoshimine.network.dto.WeeklyTrainDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface WeeklyTrainService {

    @GET("/treinos")
    suspend fun fetchWeeklyTrains(): List<WeeklyTrainDTO>

    @GET("/treinos/{id}")
    suspend fun fetchWeeklyTrain(@Path("id") id: Int): WeeklyTrainDTO
}
