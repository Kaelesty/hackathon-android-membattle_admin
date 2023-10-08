package com.kaelesty.membattleadmin.data.remote

import com.kaelesty.membattleadmin.data.dtos.BattleStatusDto
import retrofit2.Response
import retrofit2.http.GET

interface BattleApiService {

	@GET("event/Start")
	suspend fun start(): Response<Unit>

	@GET("event/Stop")
	suspend fun stop(): Response<Unit>

	@GET("event/Restart")
	suspend fun restart(): Response<Unit>

	@GET("event/GetBattleStatus")
	suspend fun getBattleStatus(): Response<BattleStatusDto>
}