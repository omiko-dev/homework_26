package com.example.homework_26.domain.model

data class ItemModel(
    val id: String,
    val name: String,
    val nameDe: String,
    val main: String?,
    val children: List<ItemModel>? = null,
    val createAt: String,
    val bglNumber: String?,
    val bglVariant: String?,
    val orderId: Int?
)