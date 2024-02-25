package com.example.homework_26.data.repository

import android.util.Log
import com.example.homework_26.data.common.Resource
import com.example.homework_26.data.remote.mapper.toDomain
import com.example.homework_26.data.remote.service.ItemService
import com.example.homework_26.domain.model.ItemModel
import com.example.homework_26.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemService: ItemService
) : ItemRepository {
    override suspend fun getItems(): Flow<Resource<List<ItemModel>>> = flow {
        try {
            emit(Resource.Loader(loader = true))
            val data = itemService.getItems()
            if (data.isSuccessful) {
                emit(Resource.Success(success = data.body()!!.map { it.toDomain() }))
            } else {
                emit(Resource.Error(error = data.errorBody()?.string() ?: "Unknown Error"))
            }
        } catch (e: Throwable) {
            Log.e("Error", "Error during API call:", e)
            emit(Resource.Error(error = e.message ?: "Unknown Error"))
        } finally {
            emit(Resource.Loader(loader = false))
        }
    }
}
