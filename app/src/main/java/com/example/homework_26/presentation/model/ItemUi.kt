package com.example.homework_26.presentation.model

data class ItemUi(
    val id: String,
    val name: String,
    val nameDe: String,
    val main: String?,
    val children: List<ItemUi>? = null,
    val createAt: String,
    val bglNumber: String?,
    val bglVariant: String?,
    val orderId: Int?
)