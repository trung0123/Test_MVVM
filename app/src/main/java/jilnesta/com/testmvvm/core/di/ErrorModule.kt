package jilnesta.com.testmvvm.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jilnesta.com.testmvvm.core.data.error.mapper.ErrorMapper
import jilnesta.com.testmvvm.core.data.error.mapper.ErrorMapperSource
import jilnesta.com.testmvvm.core.usecase.errors.ErrorManager
import jilnesta.com.testmvvm.core.usecase.errors.ErrorUseCase
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