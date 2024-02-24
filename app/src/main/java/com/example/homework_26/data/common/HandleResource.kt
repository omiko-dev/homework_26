package com.example.homework_26.data.common

import android.util.Log
import android.util.Log.e
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response


class HandleResource {
    fun <T> handleResource(call: suspend () -> Response<T>): Flow<Resource<T>> = flow {
        try {
            emit(Resource.Loader(loader = true))
            val data = call()
            Log.d("HandleResource", "API call successful: ${data.isSuccessful}") // Check this log
            if (data.isSuccessful) {
                emit(Resource.Success(success = data.body()!!))
            } else {
                emit(Resource.Error(error = data.errorBody()?.string() ?: "Unknown Error"))
            }
        } catch (e: Throwable) {
            Log.e("HandleResource", "Error during API call:", e) // Log the exact error
            emit(Resource.Error(error = e.message ?: "Unknown Error"))
        } finally {
            emit(Resource.Loader(loader = false))
        }
    }
}