package jilnesta.com.testmvvm.usecase.errors

import jilnesta.com.testmvvm.data.error.Error

interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
