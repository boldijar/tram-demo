package com.luas.data.network.base

sealed class ApiResponse<T> {
    class Success<T>(val result: T) : ApiResponse<T>()
    class Error<T>(val errorMessage: String, val exception: Exception) : ApiResponse<T>()
}