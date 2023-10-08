package com.kaelesty.membattleadmin.di

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import com.kaelesty.membattleadmin.presentation.fragments.battle.BattleFragment
import dagger.BindsInstance
import dagger.Component

@Component(
	modules = [
		ViewModelModule::class,
		DomainModule::class,
		ApiModule::class,
	]
)
@ApplicationScope
interface ApplicationComponent {

	fun inject(fragment: BattleFragment)

	@Component.Factory
	interface Factory {

		fun create(
			@BindsInstance application: Application
		): ApplicationComponent
	}
}