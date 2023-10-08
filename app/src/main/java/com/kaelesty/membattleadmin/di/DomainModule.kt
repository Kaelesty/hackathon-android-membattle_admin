package com.kaelesty.membattleadmin.di

import com.kaelesty.membattleadmin.data.repos.BattleRepo
import com.kaelesty.membattleadmin.domain.repos.IBattlesRepo
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DomainModule {

	@Binds
	@ApplicationScope
	abstract fun provideBattleRepo(impl: BattleRepo): IBattlesRepo
}