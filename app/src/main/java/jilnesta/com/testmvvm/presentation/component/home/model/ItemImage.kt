package jilnesta.com.testmvvm.presentation.component.home.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ItemImage(
    @SerializedName("name")
    var name: String,
    @SerializedName("d_type")
    var type: String
) : Serializable
