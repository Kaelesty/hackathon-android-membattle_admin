package com.kaelesty.membattleadmin.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface BattleApiService {

	@GET("event/Start")
	suspend fun start(): Response<Unit>

	@GET("event/Stop")
	suspend fun stop(): Response<Unit>

}