package com.kaelesty.membattleadmin.data.repos

import com.kaelesty.membattleadmin.domain.entities.Meme
import com.kaelesty.membattleadmin.domain.repos.IMemesRepo

class MemesRepo: IMemesRepo {

	private val memes = mutableListOf(
		Meme(0, "https://7796-5-17-5-252.ngrok-free.app/uploads/ava.jpg"),
		Meme(1, "https://7796-5-17-5-252.ngrok-free.app/uploads/ava.jpg"),
		Meme(2, "https://7796-5-17-5-252.ngrok-free.app/uploads/ava.jpg"),
		Meme(3, "https://7796-5-17-5-252.ngrok-free.app/uploads/ava.jpg"),
		Meme(4, "https://7796-5-17-5-252.ngrok-free.app/uploads/ava.jpg"),
		Meme(5, "https://7796-5-17-5-252.ngrok-free.app/uploads/ava.jpg"),
	)

	override suspend fun getMemes(): List<Meme> {
		return memes
	}

	override suspend fun banMeme(id: Int) {
		memes.removeIf { it.id == id }
	}
}