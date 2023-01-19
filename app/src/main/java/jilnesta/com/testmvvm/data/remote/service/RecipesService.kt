package jilnesta.com.testmvvm.data.remote.service

import jilnesta.com.testmvvm.data.dto.recipes.RecipesItem
import retrofit2.Response
import retrofit2.http.GET

interface RecipesService {
    @GET("recipes.json")
    suspend fun fetchRecipes(): Response<List<RecipesItem>>
}