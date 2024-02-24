package com.example.homework_26.domain.repository

import com.example.homework_26.data.common.Resource
import com.example.homework_26.domain.model.ItemModel
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun getItems(): Flow<Resource<List<ItemModel>>>
}