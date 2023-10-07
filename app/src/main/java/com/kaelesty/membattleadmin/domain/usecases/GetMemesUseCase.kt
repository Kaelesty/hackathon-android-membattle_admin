package com.kaelesty.membattleadmin.domain.usecases

import com.kaelesty.membattleadmin.domain.entities.Meme
import com.kaelesty.membattleadmin.domain.repos.IMemesRepo

class GetMemesUseCase(private val repo: IMemesRepo) {

	suspend fun getMemes(): List<Meme> = repo.getMemes()
}