package jilnesta.com.testmvvm.presentation.component.home.remote.service

import jilnesta.com.testmvvm.core.data.dto.BaseResponse
import jilnesta.com.testmvvm.presentation.component.home.model.DataHome
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {
    @GET("home")
    suspend fun getHome(): Response<BaseResponse<DataHome>>
}