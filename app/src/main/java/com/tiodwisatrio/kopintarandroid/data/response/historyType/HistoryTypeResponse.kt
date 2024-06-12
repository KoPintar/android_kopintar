package com.tiodwisatrio.kopintarandroid.data.response.historyType

import com.google.gson.annotations.SerializedName

data class HistoryTypeResponse(

	@field:SerializedName("data")
	val data: List<HistoryTypeResult?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class HistoryTypeResult(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("classResult")
	val classResult: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("numericResult")
	val numericResult: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)