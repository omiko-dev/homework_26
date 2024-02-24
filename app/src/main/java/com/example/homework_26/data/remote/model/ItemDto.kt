package com.example.homework_26.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemDto(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "name_de") val nameDe: String,
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "bgl_number") val bglNumber: String?,
    @Json(name = "bgl_variant") val bglVariant: String?,
    @Json(name = "order_id") val orderId: Int?,
    @Json(name = "main") val main: String?,
    @Json(name = "children") val children: List<ItemDto>? = null,
)