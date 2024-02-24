package com.example.homework_26.data.repository

import android.util.Log
import com.example.homework_26.data.common.HandleResource
import com.example.homework_26.data.common.Resource
import com.example.homework_26.data.common.resourceMapper
import com.example.homework_26.data.remote.mapper.toDomain
import com.example.homework_26.data.remote.service.ItemService
import com.example.homework_26.domain.model.ItemModel
import com.example.homework_26.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemService: ItemService,
    private val handleResource: HandleResource,
) : ItemRepository {
    override suspend fun getItems(): Flow<Resource<List<ItemModel>>> = flow {
//        handleResource.handleResource {
//            itemService.getItems()
//        }.map { resource ->
//            resource.resourceMapper { itemDto ->
//                itemDto.map {
//                    it.toDomain()
//                }
//            }
//        }
        try {
            emit(Resource.Loader(loader = true))
            val data = itemService.getItems()
            if (data.isSuccessful) {
                emit(Resource.Success(success = data.body()!!.map { it.toDomain() }))
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
