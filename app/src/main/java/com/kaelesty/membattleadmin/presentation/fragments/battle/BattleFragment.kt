package com.kaelesty.membattleadmin.presentation.fragments.battle

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.kaelesty.membattleadmin.AdminApplication
import com.kaelesty.membattleadmin.databinding.FragmentBattleBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import javax.inject.Inject

class BattleFragment : Fragment() {

	private val component by lazy {
		(requireActivity().application as AdminApplication).component
	}

	private var awaitingJob: Job = Job()

	@Inject
	lateinit var viewModel: BattleViewModel

	private val binding by lazy {
		FragmentBattleBinding.inflate(
			layoutInflater
		)
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		component.inject(this@BattleFragment)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		with(binding) {
			buttonStart.setOnClickListener {
				startAwaiting()
				viewModel.startBattle()
			}

			buttonStop.setOnClickListener {
				startAwaiting()
				viewModel.stopBattle()
			}

			tvResult.setOnClickListener {
				startAwaiting()
			}
		}

		viewModel.result.observe(requireActivity()) {
			if (!awaitingJob.isCancelled) {
				awaitingJob.cancel()
			}
			binding.tvResult.text = it
		}

		startAwaiting()
	}

	private fun startAwaiting() {
		if (!awaitingJob.isCancelled) {
			awaitingJob.cancel()
		}
		awaitingJob = lifecycleScope.launch(Dispatchers.IO) {
			while (true) {
				for (i in 0..3) {
					delay(700)
					binding.tvResult.text = "Awaiting ${".".repeat(i)}"
				}
			}
		}

		lifecycleScope.launch(Dispatchers.IO) {
			awaitingJob.join()
		}
	}

	companion object {
		@JvmStatic
		fun newInstance() = BattleFragment()
	}
}