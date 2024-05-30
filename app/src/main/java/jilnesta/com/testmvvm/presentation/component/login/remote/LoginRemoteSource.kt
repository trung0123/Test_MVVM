package jilnesta.com.testmvvm.presentation.component.login.remote

import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.presentation.component.login.model.LoginResponse

interface LoginRemoteSource {
    suspend fun doLogin(userName: String, passWord: String, token: String?): Resource<LoginResponse>
}