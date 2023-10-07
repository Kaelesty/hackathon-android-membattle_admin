package com.kaelesty.membattleadmin.presentation.fragments.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaelesty.membattleadmin.data.repos.MemesRepo
import com.kaelesty.membattleadmin.domain.entities.Meme
import com.kaelesty.membattleadmin.domain.repos.IMemesRepo
import com.kaelesty.membattleadmin.domain.usecases.BanMemeUseCase
import com.kaelesty.membattleadmin.domain.usecases.GetMemesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {

	private val memesRepo: IMemesRepo = MemesRepo()

	private val getMemesUseCase = GetMemesUseCase(memesRepo)
	private val banMemeUseCase = BanMemeUseCase(memesRepo)

	private val _memes: MutableLiveData<List<Meme>> = MutableLiveData()
	val memes: LiveData<List<Meme>> get() = _memes

	init {
		viewModelScope.launch(Dispatchers.IO) {
			while (true) {
				loadMemes()
			}
		}
	}

	fun loadMemes() {
		viewModelScope.launch(Dispatchers.IO) {
			_memes.postValue(getMemesUseCase.getMemes())
		}
	}

	fun banMeme(id: Int) {
		viewModelScope.launch(Dispatchers.IO) {
			banMemeUseCase.banMeme(id)
		}
	}
}