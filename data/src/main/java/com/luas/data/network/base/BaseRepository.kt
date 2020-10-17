package com.luas.data.network.base

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository {

    suspend fun <T> doRequest(request: suspend () -> T): ApiResponse<T> {
        return withContext(Dispatchers.IO) {
            try {
                ApiResponse.Success(request())
            } catch (exception: Exception) {
                Log.e("BaseRepository", "Error", exception)
                ApiResponse.Error<T>(
                    getErrorMessage(exception),
                    exception
                )
            }
        }
    }

    private fun getErrorMessage(exception: Exception): String {
        Log.e("BaseRepository", "Error", exception)
        if (exception is MessageException) {
            exception.message?.let {
                return it
            }
        }
        return "Unknown error"
    }
}