package jilnesta.com.testmvvm.presentation.component.home.remote

import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.presentation.component.home.model.DataHome

interface HomeRemoteSource {
    suspend fun getHome(): Resource<DataHome>
}