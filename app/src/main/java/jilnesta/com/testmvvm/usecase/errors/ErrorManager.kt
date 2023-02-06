package jilnesta.com.testmvvm.usecase.errors

import jilnesta.com.testmvvm.data.error.Error
import jilnesta.com.testmvvm.data.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 * Created by AhmedEltaher
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: Int): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap.getValue(errorCode))
    }
}
