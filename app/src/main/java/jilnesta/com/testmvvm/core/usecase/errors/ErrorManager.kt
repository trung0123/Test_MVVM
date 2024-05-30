package jilnesta.com.testmvvm.core.usecase.errors

import jilnesta.com.testmvvm.core.data.error.Error
import jilnesta.com.testmvvm.core.data.error.mapper.ErrorMapper
import javax.inject.Inject

/**
 * Created by AhmedEltaher
 */

class ErrorManager @Inject constructor(private val errorMapper: ErrorMapper) : ErrorUseCase {
    override fun getError(errorCode: String): Error {
        return Error(code = errorCode, description = errorMapper.errorsMap[errorCode]!!)
    }
}
