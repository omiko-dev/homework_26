package com.example.homework_26.data.remote.service

import com.example.homework_26.data.remote.model.ItemDto
import retrofit2.Response
import retrofit2.http.GET

interface ItemService {
    @GET("6f722f19-3297-4edd-a249-fe765e8104db")
    suspend fun getItems(): Response<List<ItemDto>>
}