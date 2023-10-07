package com.kaelesty.membattleadmin.presentation.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.kaelesty.membattleadmin.databinding.FragmentListBinding
import com.kaelesty.membattleadmin.presentation.adapters.MemeListAdapter

class ListFragment : Fragment() {

	private val viewModel by lazy {
		ViewModelProvider(requireActivity())[ListViewModel::class.java]
	}

	private val binding by lazy {
		FragmentListBinding.inflate(
			layoutInflater
		)
	}

	val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
		override fun onMove(
			recyclerView: RecyclerView,
			viewHolder: RecyclerView.ViewHolder,
			target: RecyclerView.ViewHolder
		): Boolean {
			return false
		}

		override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
			val position = viewHolder.adapterPosition
			with(viewModel) {
				banMeme(adapter.getMemeId(position))
				loadMemes()
			}
		}
	})

	private val adapter by lazy {
		MemeListAdapter(requireContext(), this@ListFragment)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.recycler.adapter = adapter

		viewModel.memes.observe(requireActivity()) {
			adapter.submitList(it.toMutableList())
		}

		itemTouchHelper.attachToRecyclerView(binding.recycler)
	}

	companion object {
		@JvmStatic
		fun newInstance() = ListFragment()
	}
}