package jilnesta.com.testmvvm.presentation.component.main.remote

import jilnesta.com.testmvvm.core.data.remote.BaseRemote
import jilnesta.com.testmvvm.core.data.remote.ServiceGenerator
import jilnesta.com.testmvvm.utils.NetworkConnectivity
import javax.inject.Inject

class MainRemote @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity
) : BaseRemote(), MainRemoteSource {


}