package jilnesta.com.testmvvm.core.usecase.errors

import jilnesta.com.testmvvm.core.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: String): Error
}
