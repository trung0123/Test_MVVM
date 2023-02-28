package jilnesta.com.testmvvm.data.remote

import jilnesta.com.testmvvm.data.Resource
import jilnesta.com.testmvvm.data.dto.login.LoginRequest
import jilnesta.com.testmvvm.data.dto.login.LoginResponse
import jilnesta.com.testmvvm.data.dto.recipes.Recipes

interface RemoteDataSource {
    suspend fun requestRecipes(): Resource<Recipes>

    suspend fun doLogin(userName: String, passWord: String, token: String?): Resource<LoginResponse>
}