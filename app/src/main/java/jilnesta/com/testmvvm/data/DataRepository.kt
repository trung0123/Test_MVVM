package jilnesta.com.testmvvm.data

import jilnesta.com.testmvvm.data.dto.login.LoginResponse
import jilnesta.com.testmvvm.data.dto.recipes.Recipes
import jilnesta.com.testmvvm.data.local.LocalData
import jilnesta.com.testmvvm.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(
    private val remoteRepository: RemoteData,
    private val localRepository: LocalData,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {

    override suspend fun requestRecipes(): Flow<Resource<Recipes>> {
        return flow {
            emit(remoteRepository.requestRecipes())
        }.flowOn(ioDispatcher)
    }

    override suspend fun doLogin(
        userName: String,
        passWord: String,
        token: String?
    ): Flow<Resource<LoginResponse>> {
        return flow {
            emit(remoteRepository.doLogin(userName, passWord, token))
        }.flowOn(ioDispatcher)
    }

    override suspend fun addToFavorite(id: String): Flow<Resource<Boolean>> {
        return flow {
            localRepository.getCachedFavorites().let {
                it.data?.toMutableSet()?.let { set ->
                    val isAdded = set.add(id)
                    if (isAdded) {
                        emit(localRepository.cacheFavorites(set))
                    } else {
                        emit(Resource.Success(false))
                    }
                }
                it.errorCode?.let { errorCode ->
                    emit(Resource.DataError<Boolean>(errorCode))
                }
            }
        }
    }

    override suspend fun removeFromFavorite(id: String): Flow<Resource<Boolean>> {
        return flow {
            emit(localRepository.removeFromFavorites(id))
        }.flowOn(ioDispatcher)
    }

    override suspend fun isFavorite(id: String): Flow<Resource<Boolean>> {
        return flow {
            emit(localRepository.isFavorite(id))
        }.flowOn(ioDispatcher)
    }
}