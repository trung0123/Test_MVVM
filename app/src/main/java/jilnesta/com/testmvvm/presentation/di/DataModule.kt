package jilnesta.com.testmvvm.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jilnesta.com.testmvvm.presentation.component.home.remote.repository.HomeRepository
import jilnesta.com.testmvvm.presentation.component.home.remote.repository.HomeRepositorySource
import jilnesta.com.testmvvm.presentation.component.login.remote.repository.LoginRepository
import jilnesta.com.testmvvm.presentation.component.login.remote.repository.LoginRepositorySource
import jilnesta.com.testmvvm.presentation.component.main.remote.repository.MainRepository
import jilnesta.com.testmvvm.presentation.component.main.remote.repository.MainRepositorySource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    @Singleton
    abstract fun provideLoginRepository(dataRepository: LoginRepository): LoginRepositorySource

    @Binds
    @Singleton
    abstract fun provideMainRepository(dataRepository: MainRepository): MainRepositorySource

    @Binds
    @Singleton
    abstract fun provideHomeRepository(dataRepository: HomeRepository): HomeRepositorySource

}