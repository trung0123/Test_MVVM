package jilnesta.com.testmvvm.presentation.component.login.remote.repository

import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.presentation.component.login.model.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginRepositorySource {
    suspend fun doLogin(userName: String,
                        passWord: String,
                        token: String?): Flow<Resource<LoginResponse>>
}