package jilnesta.com.testmvvm.presentation.component.main.remote.repository

import jilnesta.com.testmvvm.core.data.local.LocalData
import jilnesta.com.testmvvm.presentation.component.main.remote.MainRemote
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainRepository @Inject constructor(
    private val remoteRepository: MainRemote,
    private val localRepository: LocalData,
    private val ioDispatcher: CoroutineContext
) : MainRepositorySource {

}