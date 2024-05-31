package jilnesta.com.testmvvm.presentation.component.main.vm

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jilnesta.com.testmvvm.core.data.dto.Resource
import jilnesta.com.testmvvm.core.base.BaseViewModel
import jilnesta.com.testmvvm.presentation.component.main.model.Recipes
import jilnesta.com.testmvvm.presentation.component.main.model.RecipesItem
import jilnesta.com.testmvvm.presentation.component.main.remote.repository.MainRepositorySource
import jilnesta.com.testmvvm.utils.SingleEvent
import jilnesta.com.testmvvm.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import java.util.Locale.ROOT
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataRepository: MainRepositorySource) :
    BaseViewModel() {

}








