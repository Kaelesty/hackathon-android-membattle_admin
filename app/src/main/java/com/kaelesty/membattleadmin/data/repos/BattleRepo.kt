package com.kaelesty.membattleadmin.data.repos

import android.util.Log
import com.kaelesty.membattleadmin.data.remote.BattleApiService
import com.kaelesty.membattleadmin.domain.repos.IBattlesRepo
import javax.inject.Inject

class BattleRepo @Inject constructor(private val battleApiService: BattleApiService): IBattlesRepo {

	override suspend fun startBattle(): Int {
		val response = battleApiService.start()
		return response.code()
	}

	override suspend fun stopBattle(): Int {
		val response = battleApiService.stop()
		return response.code()
	}

	override suspend fun restart(): Int {
		val response = battleApiService.restart()
		return response.code()
	}

	override suspend fun getBattleStatus(): String {
		val response = battleApiService.getBattleStatus()
		Log.d("REPO", response.body()?.battleStatus ?: "ERROR")
		return when(response.code()) {
			200 -> response.body()?.battleStatus ?: "ERROR"
			else -> "ERROR"
		}
	}
}