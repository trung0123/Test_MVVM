package jilnesta.com.testmvvm.data.remote.service

import jilnesta.com.testmvvm.data.dto.base.BaseResponse
import jilnesta.com.testmvvm.data.dto.login.LoginResponse
import jilnesta.com.testmvvm.data.dto.recipes.RecipesItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RecipesService {
    @GET("recipes.json")
    suspend fun fetchRecipes(): Response<List<RecipesItem>>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("auth_token") tokenFCM: String?
    ): Response<BaseResponse<LoginResponse>>
}