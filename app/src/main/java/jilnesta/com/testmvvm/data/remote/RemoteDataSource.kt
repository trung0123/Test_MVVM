package jilnesta.com.testmvvm.data.remote

import jilnesta.com.testmvvm.data.Resource
import jilnesta.com.testmvvm.data.dto.recipes.Recipes

interface RemoteDataSource {
    suspend fun requestRecipes(): Resource<Recipes>
}