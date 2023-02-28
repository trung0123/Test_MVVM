package jilnesta.com.testmvvm.data.remote

import jilnesta.com.testmvvm.data.Resource
import jilnesta.com.testmvvm.data.dto.base.BaseResponse
import jilnesta.com.testmvvm.data.dto.login.LoginResponse
import jilnesta.com.testmvvm.data.dto.recipes.Recipes
import jilnesta.com.testmvvm.data.dto.recipes.RecipesItem
import jilnesta.com.testmvvm.data.error.NETWORK_ERROR
import jilnesta.com.testmvvm.data.error.NO_INTERNET_CONNECTION
import jilnesta.com.testmvvm.data.remote.service.RecipesService
import jilnesta.com.testmvvm.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteData @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) : RemoteDataSource {

    override suspend fun requestRecipes(): Resource<Recipes> {
        val recipesService = serviceGenerator.createService(RecipesService::class.java)
        return when (val response = processCall(recipesService::fetchRecipes)) {
            is List<*> -> {
                @Suppress("UNCHECKED_CAST")
                Resource.Success(data = Recipes(response as ArrayList<RecipesItem>))
            }
            else -> {
                Resource.DataError(errorCode = response.toString())
            }
        }
    }

    override suspend fun doLogin(
        userName: String,
        passWord: String,
        token: String?
    ): Resource<LoginResponse> {
        val recipesService = serviceGenerator.createService(RecipesService::class.java)
        return when (val response =
            processCall1 { recipesService.login(userName, passWord, token) }) {
            is LoginResponse -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response.toString())
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }

    private suspend fun processCall1(responseCall: suspend () -> Response<*>): Any? {
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                val baseResponse = response.body() as BaseResponse<*>
                if(baseResponse.success) {
                    baseResponse.data
                } else {
                    baseResponse.code
                }
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}