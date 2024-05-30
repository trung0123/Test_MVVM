package jilnesta.com.testmvvm.presentation.component.login.remote

import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.core.data.remote.BaseRemote
import jilnesta.com.testmvvm.core.data.remote.ServiceGenerator
import jilnesta.com.testmvvm.presentation.component.login.model.LoginResponse
import jilnesta.com.testmvvm.presentation.component.login.remote.service.LoginService
import jilnesta.com.testmvvm.utils.NetworkConnectivity
import javax.inject.Inject

class LoginRemote @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) : BaseRemote(), LoginRemoteSource {

    override suspend fun doLogin(
        userName: String,
        passWord: String,
        token: String?
    ): Resource<LoginResponse> {
        val recipesService = serviceGenerator.createService(LoginService::class.java)
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

//    private suspend fun processCall1(responseCall: suspend () -> Response<*>): Any? {
//        return try {
//            val response = responseCall.invoke()
//            val responseCode = response.code()
//            if (response.isSuccessful) {
//                val baseResponse = response.body() as BaseResponse<*>
//                if(baseResponse.success) {
//                    baseResponse.data
//                } else {
//                    baseResponse.code
//                }
//            } else {
//                responseCode
//            }
//        } catch (e: IOException) {
//            NETWORK_ERROR
//        }
//    }
}