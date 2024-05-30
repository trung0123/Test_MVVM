package jilnesta.com.testmvvm.presentation.component.home.remote

import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.core.data.remote.BaseRemote
import jilnesta.com.testmvvm.core.data.remote.ServiceGenerator
import jilnesta.com.testmvvm.presentation.component.home.model.DataHome
import jilnesta.com.testmvvm.presentation.component.home.remote.service.HomeService
import jilnesta.com.testmvvm.utils.NetworkConnectivity
import javax.inject.Inject

class HomeRemote @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) : BaseRemote(), HomeRemoteSource {

    override suspend fun getHome(): Resource<DataHome> {
        val recipesService = serviceGenerator.createService(HomeService::class.java)
        return when (val response =
            processCall1 { recipesService.getHome() }) {
            is DataHome -> {
                Resource.Success(data = response)
            }

            else -> {
                Resource.DataError(errorCode = response.toString())
            }
        }
    }
}