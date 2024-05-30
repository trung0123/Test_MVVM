package jilnesta.com.testmvvm.presentation.component.main.remote

import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.core.data.error.NETWORK_ERROR
import jilnesta.com.testmvvm.core.data.error.NO_INTERNET_CONNECTION
import jilnesta.com.testmvvm.core.data.remote.BaseRemote
import jilnesta.com.testmvvm.core.data.remote.ServiceGenerator
import jilnesta.com.testmvvm.presentation.component.main.model.Recipes
import jilnesta.com.testmvvm.presentation.component.main.model.RecipesItem
import jilnesta.com.testmvvm.presentation.component.main.remote.service.MainService
import jilnesta.com.testmvvm.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class MainRemote @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) : BaseRemote(), MainRemoteSource {

    override suspend fun requestRecipes(): Resource<Recipes> {
        val recipesService = serviceGenerator.createService(MainService::class.java)
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
}