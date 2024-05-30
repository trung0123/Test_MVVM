package jilnesta.com.testmvvm.core.data.dto

import com.google.gson.annotations.SerializedName

open class BaseResponse<T>(
    @SerializedName("success")
    val success: Boolean = false,
    @SerializedName("code")
    val code: String = "",
    @SerializedName("msg")
    val msg: String = "",
    @SerializedName("data")
    val data: T
)

class BaseData<T>(
    val pagination: Pagination,
    val items: List<T>,
    @SerializedName("can_edit")
    val canEdit: Boolean = false,
    @SerializedName("can_apply")
    val canApply: Boolean = false
)

class BaseResponseData<T>(success: Boolean, code: String, msg: String, data: BaseData<T>) :
    BaseResponse<BaseData<T>>(success, code, msg, data)