package com.kaelesty.membattleadmin.presentation.fragments.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaelesty.membattleadmin.domain.usecases.GetBattleStatusUseCase
import com.kaelesty.membattleadmin.domain.usecases.RestartUseCase
import com.kaelesty.membattleadmin.domain.usecases.StartBattleUseCase
import com.kaelesty.membattleadmin.domain.usecases.StopBattleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class BattleViewModel @Inject constructor(): ViewModel() {

	private val _responseResult = MutableLiveData<String>()
	val responseResult: LiveData<String> get() = _responseResult

	private val _battleStatus = MutableLiveData<String>()
	val battleStatus: LiveData<String> get() = _battleStatus

	@Inject lateinit var startBattleUseCase: StartBattleUseCase
	@Inject lateinit var stopBattleUseCase: StopBattleUseCase
	@Inject lateinit var restartUseCase: RestartUseCase
	@Inject lateinit var getBattleStatusUseCase: GetBattleStatusUseCase

	fun loadBattleStatus() {
		viewModelScope.launch(Dispatchers.IO) {
			while (true) {
				_battleStatus.postValue(
					getBattleStatusUseCase()
				)
				delay(1000)
			}
		}
	}

	fun startBattle() {
		viewModelScope.launch(Dispatchers.IO) {
			val rc = startBattleUseCase()
			_responseResult.postValue("HTTP$rc")
		}
	}

	fun stopBattle() {
		viewModelScope.launch(Dispatchers.IO) {
			val rc = stopBattleUseCase()
			_responseResult.postValue("HTTP$rc")
		}
	}

	fun restart() {
		viewModelScope.launch(Dispatchers.IO) {
			val rc = restartUseCase()
			_responseResult.postValue("HTTP$rc")
		}
	}

}