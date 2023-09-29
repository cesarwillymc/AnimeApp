package com.cesarwillymc.animeapp.util.state

import android.util.Log

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
val Result<*>.isSuccess
    get() = this is Result.Success
val Result<*>.isError
    get() = this is Result.Error
fun <T, R> Result<T>.map(transform: T.() -> R) =
    if (isSuccess) Result.Success(transform(getData())) else Result.Error(getError())
fun <T> Result<T>.getData() = (this as Result.Success).data
fun <T> Result<T>.getError() = (this as Result.Error).exception
fun <T> Result<T>.errorOrNull() = if (this.isError) this.getError() else null
fun <T> Result<T>.dataOrNull() = if (this.isSuccess) this.getData() else null
suspend fun <Out> getResult(
    call: suspend () -> Out
): Result<Out> = try {
    Result.Success(call())
} catch (e: Exception) {
    Result.Error(exception = e)
}
