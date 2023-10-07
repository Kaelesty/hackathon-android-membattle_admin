package com.kaelesty.membattleadmin.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaelesty.membattleadmin.R
import com.kaelesty.membattleadmin.databinding.ActivityMainBinding
import com.kaelesty.membattleadmin.presentation.fragments.battle.BattleFragment
import com.kaelesty.membattleadmin.presentation.fragments.list.ListFragment

class MainActivity : AppCompatActivity() {

	private val binding by lazy {
		ActivityMainBinding.inflate(
			layoutInflater
		)
	}

	private val listFragment by lazy {
		ListFragment.newInstance()
	}

	private val battleFragment by lazy {
		BattleFragment.newInstance()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		with(binding) {

			supportFragmentManager.beginTransaction()
				.replace(R.id.fragment_container, listFragment)
				.commit()

			navbuttonList.setOnClickListener {
				supportFragmentManager.beginTransaction()
					.replace(R.id.fragment_container, listFragment)
					.commit()
			}

			navbuttonBattle.setOnClickListener {
				supportFragmentManager.beginTransaction()
					.replace(R.id.fragment_container, battleFragment)
					.commit()
			}
		}
	}
}