package com.kaelesty.membattleadmin

import android.app.Application
import com.kaelesty.membattleadmin.di.DaggerApplicationComponent

class AdminApplication: Application() {

	val component by lazy {
		DaggerApplicationComponent.factory().create(this@AdminApplication)
	}
}