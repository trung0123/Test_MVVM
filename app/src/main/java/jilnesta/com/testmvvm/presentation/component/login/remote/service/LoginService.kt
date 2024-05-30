package jilnesta.com.testmvvm.presentation.component.login.remote.service

import jilnesta.com.testmvvm.core.data.dto.BaseResponse
import jilnesta.com.testmvvm.presentation.component.login.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("auth_token") tokenFCM: String?
    ): Response<BaseResponse<LoginResponse>>
}