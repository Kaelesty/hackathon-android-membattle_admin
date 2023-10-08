package com.kaelesty.membattleadmin.presentation.fragments.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaelesty.membattleadmin.data.repos.BattleRepo
import com.kaelesty.membattleadmin.domain.repos.IBattlesRepo
import com.kaelesty.membattleadmin.domain.usecases.StartBattleUseCase
import com.kaelesty.membattleadmin.domain.usecases.StopBattleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class BattleViewModel @Inject constructor(): ViewModel() {

	private val _result = MutableLiveData<String>()
	val result: LiveData<String> get() = _result

	@Inject lateinit var startBattleUseCase: StartBattleUseCase
	@Inject lateinit var stopBattleUseCase: StopBattleUseCase

	fun startBattle() {
		viewModelScope.launch(Dispatchers.IO) {
			val rc = startBattleUseCase()
			_result.postValue("HTTP${rc.toString()}")
		}
	}

	fun stopBattle() {
		viewModelScope.launch(Dispatchers.IO) {
			val rc = stopBattleUseCase()
			_result.postValue("HTTP${rc.toString()}")
		}
	}

}