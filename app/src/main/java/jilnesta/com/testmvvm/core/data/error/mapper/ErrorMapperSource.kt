package jilnesta.com.testmvvm.core.data.error.mapper

interface ErrorMapperSource {
    fun getErrorString(errorId: Int): String
    val errorsMap: Map<String, String>
}