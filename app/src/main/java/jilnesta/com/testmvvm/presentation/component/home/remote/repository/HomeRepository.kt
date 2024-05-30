package jilnesta.com.testmvvm.presentation.component.home.remote.repository

import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.core.data.local.LocalData
import jilnesta.com.testmvvm.presentation.component.home.model.DataHome
import jilnesta.com.testmvvm.presentation.component.home.remote.HomeRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HomeRepository @Inject constructor(
    private val remoteRepository: HomeRemote,
    private val localRepository: LocalData,
    private val ioDispatcher: CoroutineContext,
) : HomeRepositorySource {

    override suspend fun getHome(): Flow<Resource<DataHome>> {
        return flow {
            emit(remoteRepository.getHome())
        }.flowOn(ioDispatcher)
    }
}