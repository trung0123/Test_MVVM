package jilnesta.com.testmvvm.presentation.component.home.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImgLesson(
    @SerializedName("display")
    var display: Int,
    @SerializedName("items")
    var items: List<ItemImage>,
    @SerializedName("base_url")
    var baseUrl: String
) : Serializable