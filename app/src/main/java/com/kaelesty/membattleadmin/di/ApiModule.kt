package com.kaelesty.membattleadmin.di

import com.kaelesty.membattleadmin.data.remote.BattleApiService
import com.kaelesty.membattleadmin.data.remote.BattleServiceFactory
import com.kaelesty.membattleadmin.data.repos.BattleRepo
import com.kaelesty.membattleadmin.domain.repos.IBattlesRepo
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class ApiModule {

	@Provides
	@ApplicationScope
	fun provideBattleApiService(): BattleApiService {
		return BattleServiceFactory.apiService
	}
}