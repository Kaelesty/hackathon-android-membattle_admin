package com.kaelesty.membattleadmin.domain.repos

import com.kaelesty.membattleadmin.domain.entities.Meme

interface IMemesRepo {

	suspend fun getMemes(): List<Meme>

	suspend fun banMeme(id: Int)
}