package jilnesta.com.testmvvm.presentation.component.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.core.base.BaseViewModel
import jilnesta.com.testmvvm.presentation.component.home.model.DataHome
import jilnesta.com.testmvvm.presentation.component.home.remote.repository.HomeRepository
import jilnesta.com.testmvvm.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataRepository: HomeRepository) :
    BaseViewModel() {
    private val homeLiveDataPrivate = MutableLiveData<Resource<DataHome>>()
    val homeLiveData: LiveData<Resource<DataHome>> get() = homeLiveDataPrivate

    fun getHome() {
        viewModelScope.launch {
            homeLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepository.getHome().collect {
                    homeLiveDataPrivate.value = it
                }
            }
        }
    }
}