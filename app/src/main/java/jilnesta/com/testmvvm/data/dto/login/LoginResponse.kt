package jilnesta.com.testmvvm.data.dto.login

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class LoginResponse(
    @SerializedName("token")
    var token: String = "",
    @SerializedName("id")
    var id: String = "",
    @SerializedName("code")
    var code: String = "",
    @SerializedName("report_state")
    var reportState: String = ""
)
