package co.king.chasetest.util

sealed class Resource<T>(data: T?, error: String?) {
    class Loading<T>: Resource<T>(null, null)
    data class Success<T>(val data: T): Resource<T>(data = data, error = null)
    data class Failure<T>(val data: T?, val error: String): Resource<T>(data = data,error = error)
}