package jilnesta.com.testmvvm.presentation.component.main.remote.service

import jilnesta.com.testmvvm.presentation.component.main.model.RecipesItem
import retrofit2.Response
import retrofit2.http.GET

interface MainService {
    @GET("recipes.json")
    suspend fun fetchRecipes(): Response<List<RecipesItem>>
}