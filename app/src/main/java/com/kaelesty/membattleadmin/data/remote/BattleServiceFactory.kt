package com.kaelesty.membattleadmin.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BattleServiceFactory {

	private const val URL = "https://a5fa-194-226-199-61.ngrok-free.app" + "/api/"

	val apiService: BattleApiService = Retrofit.Builder()
		.baseUrl(URL)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(BattleApiService::class.java)
}