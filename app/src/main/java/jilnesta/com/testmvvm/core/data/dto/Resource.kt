package jilnesta.com.testmvvm.core.data.dto

// A generic class that contains data and status about loading this data.
sealed class Resource<T>(
    val data: T? = null,
    val errorCode: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class DataError<T>(errorCode: String) : Resource<T>(null, errorCode)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$errorCode]"
            is Loading<T> -> "Loading"
        }
    }
}
