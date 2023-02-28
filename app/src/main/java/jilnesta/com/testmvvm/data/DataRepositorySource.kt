package jilnesta.com.testmvvm.data

import jilnesta.com.testmvvm.data.dto.login.LoginRequest
import jilnesta.com.testmvvm.data.dto.login.LoginResponse
import jilnesta.com.testmvvm.data.dto.recipes.Recipes
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun requestRecipes(): Flow<Resource<Recipes>>

    suspend fun doLogin(userName: String,
                        passWord: String,
                        token: String?): Flow<Resource<LoginResponse>>

    suspend fun addToFavorite(id: String): Flow<Resource<Boolean>>

    suspend fun removeFromFavorite(id: String): Flow<Resource<Boolean>>

    suspend fun isFavorite(id: String): Flow<Resource<Boolean>>
}