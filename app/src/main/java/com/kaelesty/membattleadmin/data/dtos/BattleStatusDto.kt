package com.kaelesty.membattleadmin.data.dtos

import com.google.gson.annotations.SerializedName

data class BattleStatusDto(
	@SerializedName("battleStatus") val battleStatus: String
)