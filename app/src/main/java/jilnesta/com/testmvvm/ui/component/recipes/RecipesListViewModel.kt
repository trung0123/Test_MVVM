package jilnesta.com.testmvvm.ui.component.recipes

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jilnesta.com.testmvvm.data.DataRepositorySource
import jilnesta.com.testmvvm.data.Resource
import jilnesta.com.testmvvm.data.dto.recipes.Recipes
import jilnesta.com.testmvvm.data.dto.recipes.RecipesItem
import jilnesta.com.testmvvm.ui.base.BaseViewModel
import jilnesta.com.testmvvm.utils.SingleEvent
import jilnesta.com.testmvvm.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Locale.ROOT
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(private val dataRepository: DataRepositorySource) :
    BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val recipesLiveDataPrivate = MutableLiveData<Resource<Recipes>>()
    val recipesLiveData: LiveData<Resource<Recipes>> get() = recipesLiveDataPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val recipeSearchFoundPrivate: MutableLiveData<RecipesItem> = MutableLiveData()
    val recipeSearchFound: LiveData<RecipesItem> get() = recipeSearchFoundPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val noSearchFoundPrivate: MutableLiveData<Unit> = MutableLiveData()
    val noSearchFound: LiveData<Unit> get() = noSearchFoundPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val openRecipeDetailsPrivate = MutableLiveData<SingleEvent<RecipesItem>>()
    val openRecipeDetails: LiveData<SingleEvent<RecipesItem>> get() = openRecipeDetailsPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showSnackBarPrivate = MutableLiveData<SingleEvent<Any>>()
    val showSnackBar: LiveData<SingleEvent<Any>> get() = showSnackBarPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate

    fun getRecipes() {
        viewModelScope.launch {
            recipesLiveDataPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                dataRepository.requestRecipes().collect {
                    recipesLiveDataPrivate.value = it
                }
            }
        }
    }

    fun openRecipeDetails(recipe: RecipesItem) {
        openRecipeDetailsPrivate.value = SingleEvent(recipe)
    }

    fun showToastMessage(errorCode: Int) {
//        val error = errorManager.getError(errorCode)
//        showToastPrivate.value = SingleEvent(error.description)
    }

    fun onSearchClick(recipeName: String) {
        recipesLiveDataPrivate.value?.data?.recipesList?.let {
            if (it.isNotEmpty()) {
                for (recipe in it) {
                    if (recipe.name.toLowerCase(ROOT).contains(recipeName.toLowerCase(ROOT))) {
                        recipeSearchFoundPrivate.value = recipe
                        return
                    }
                }
            }
        }
        return noSearchFoundPrivate.postValue(Unit)
    }
}








