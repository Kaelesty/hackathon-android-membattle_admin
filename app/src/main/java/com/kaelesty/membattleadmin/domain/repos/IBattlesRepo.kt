package com.kaelesty.membattleadmin.domain.repos


interface IBattlesRepo {

	suspend fun startBattle(): Int

	suspend fun stopBattle(): Int

	suspend fun restart(): Int

	suspend fun getBattleStatus(): String
}