package jilnesta.com.testmvvm.presentation.component.home.remote.repository

import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.presentation.component.home.model.DataHome
import kotlinx.coroutines.flow.Flow

interface HomeRepositorySource {
    suspend fun getHome(): Flow<Resource<DataHome>>
}