package jilnesta.com.testmvvm.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jilnesta.com.testmvvm.data.error.mapper.ErrorMapper
import jilnesta.com.testmvvm.data.error.mapper.ErrorMapperSource
import jilnesta.com.testmvvm.usecase.errors.ErrorManager
import jilnesta.com.testmvvm.usecase.errors.ErrorUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorModule {
    @Binds
    @Singleton
    abstract fun provideErrorFactoryImpl(errorManager: ErrorManager): ErrorUseCase

    @Binds
    @Singleton
    abstract fun provideErrorMapper(errorMapper: ErrorMapper): ErrorMapperSource
}