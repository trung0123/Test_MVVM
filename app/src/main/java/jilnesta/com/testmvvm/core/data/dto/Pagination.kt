package jilnesta.com.testmvvm.core.data.dto

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Pagination(
    @SerializedName("page_count")
    val page: Int = 0,
    @SerializedName("total_item_count")
    val totalItem: Int = 0
)