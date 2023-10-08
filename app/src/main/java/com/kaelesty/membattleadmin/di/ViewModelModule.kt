package com.kaelesty.membattleadmin.di

import androidx.lifecycle.ViewModel
import com.kaelesty.membattleadmin.presentation.fragments.battle.BattleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

	@IntoMap
	@ViewModelKey(BattleViewModel::class)
	@Binds
	@ApplicationScope
	fun bindCoinListViewModel(impl: BattleViewModel): ViewModel
}