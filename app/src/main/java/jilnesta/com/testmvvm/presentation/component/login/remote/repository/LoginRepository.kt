package jilnesta.com.testmvvm.presentation.component.login.remote.repository

import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.core.data.local.LocalData
import jilnesta.com.testmvvm.presentation.component.login.model.LoginResponse
import jilnesta.com.testmvvm.presentation.component.login.remote.LoginRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginRepository @Inject constructor(
    private val remoteRepository: LoginRemote,
    private val localRepository: LocalData,
    private val ioDispatcher: CoroutineContext,
) : LoginRepositorySource {

    override suspend fun doLogin(
        userName: String,
        passWord: String,
        token: String?
    ): Flow<Resource<LoginResponse>> {
        return flow {
            emit(remoteRepository.doLogin(userName, passWord, token))
        }.flowOn(ioDispatcher)
    }
}