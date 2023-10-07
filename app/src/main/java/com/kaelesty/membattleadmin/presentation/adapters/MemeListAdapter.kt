package com.kaelesty.membattleadmin.presentation.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.kaelesty.membattleadmin.R
import com.kaelesty.membattleadmin.databinding.MemeItemBinding
import com.kaelesty.membattleadmin.domain.entities.Meme
import com.kaelesty.membattleadmin.presentation.fragments.list.ListFragment

class MemeListAdapter(private val context: Context, private val fragment: ListFragment): ListAdapter<Meme, MemeListAdapter.MemeViewHolder>(object :
	DiffUtil.ItemCallback<Meme>() {
	override fun areItemsTheSame(oldItem: Meme, newItem: Meme) = oldItem.id == newItem.id

	override fun areContentsTheSame(oldItem: Meme, newItem: Meme) = oldItem.image == newItem.image
} ) {

	class MemeViewHolder(val binding: MemeItemBinding): ViewHolder(binding.root) {
		var id: Int = -1
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
		return MemeViewHolder(MemeItemBinding.inflate(LayoutInflater.from(context)))
	}

	override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
		val meme = currentList[position]
		holder.id = meme.id
		Glide.with(fragment)
			.load(meme.image)
			.placeholder(android.R.drawable.star_big_on)
			.into(holder.binding.ivMeme)
	}

	fun getMemeId(position: Int) = currentList[position].id
}