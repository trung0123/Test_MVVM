package jilnesta.com.testmvvm.core.data.remote

import jilnesta.com.testmvvm.core.data.dto.BaseResponse
import jilnesta.com.testmvvm.core.data.error.NETWORK_ERROR
import retrofit2.Response
import java.io.IOException

abstract class BaseRemote {
    suspend fun processCall1(responseCall: suspend () -> Response<*>): Any? {
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