package com.kaelesty.membattleadmin.domain.usecases

import android.util.Log
import com.kaelesty.membattleadmin.domain.repos.IMemesRepo

class BanMemeUseCase(private val repo: IMemesRepo) {

	suspend fun banMeme(id: Int) {
		Log.d("ListViewModel", id.toString())
		repo.banMeme(id)
	}

}