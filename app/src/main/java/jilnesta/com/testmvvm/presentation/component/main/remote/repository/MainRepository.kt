package jilnesta.com.testmvvm.presentation.component.main.remote.repository

import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.core.data.local.LocalData
import jilnesta.com.testmvvm.presentation.component.main.model.Recipes
import jilnesta.com.testmvvm.presentation.component.main.remote.MainRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainRepository @Inject constructor(
    private val remoteRepository: MainRemote,
    private val localRepository: LocalData,
    private val ioDispatcher: CoroutineContext
) : MainRepositorySource {

//    override suspend fun requestRecipes(): Flow<Resource<Recipes>> {
//        return flow {
//            emit(remoteRepository.requestRecipes())
//        }.flowOn(ioDispatcher)
//    }

//    override suspend fun addToFavorite(id: String): Flow<Resource<Boolean>> {
//        return flow {
////            localRepository.getCachedFavorites().let {
////                it.data?.toMutableSet()?.let { set ->
////                    val isAdded = set.add(id)
////                    if (isAdded) {
////                        emit(localRepository.cacheFavorites(set))
////                    } else {
////                        emit(Resource.Success(false))
////                    }
////                }
////                it.errorCode?.let { errorCode ->
////                    emit(Resource.DataError(errorCode))
////                }
////            }
//        }
//    }

//    override suspend fun removeFromFavorite(id: String): Flow<Resource<Boolean>> {
//        return flow {
////            emit(localRepository.removeFromFavorites(id))
//        }.flowOn(ioDispatcher)
//    }
//
//    override suspend fun isFavorite(id: String): Flow<Resource<Boolean>> {
//        return flow {
////            emit(localRepository.isFavorite(id))
//        }.flowOn(ioDispatcher)
//    }
}