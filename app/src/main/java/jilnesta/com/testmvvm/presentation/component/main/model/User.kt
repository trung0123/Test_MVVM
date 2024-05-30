package jilnesta.com.testmvvm.presentation.component.main.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class User(
    @Json(name = "email")
    val email: String = "",
    @Json(name = "latlng")
    val latlng: String = "",
    @Json(name = "name")
    val name: String = ""
) : Parcelable
