package jilnesta.com.testmvvm.presentation.component.login.model

import com.google.gson.annotations.SerializedName

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
