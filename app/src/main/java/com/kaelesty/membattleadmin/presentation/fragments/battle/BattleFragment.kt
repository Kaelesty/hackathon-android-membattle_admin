package com.kaelesty.membattleadmin.presentation.fragments.battle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.kaelesty.membattleadmin.AdminApplication
import com.kaelesty.membattleadmin.databinding.FragmentBattleBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

			buttonRestart.setOnClickListener {
				startAwaiting()
				viewModel.restart()
			}

			tvResultStatus.setOnClickListener {
				startAwaiting()
			}
		}

		viewModel.responseResult.observe(requireActivity()) {
			if (!awaitingJob.isCancelled) {
				awaitingJob.cancel()
			}
			binding.tvResultStatus.text = it
		}

		viewModel.battleStatus.observe(requireActivity()) {
			binding.tvBattleStatus.text = it
		}

		startAwaiting()
		viewModel.loadBattleStatus()
	}

	private fun startAwaiting() {
		if (!awaitingJob.isCancelled) {
			awaitingJob.cancel()
		}
		awaitingJob = lifecycleScope.launch {
			while (true) {
				for (i in 0..3) {
					delay(700)
					binding.tvResultStatus.text = "Awaiting ${".".repeat(i)}"
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