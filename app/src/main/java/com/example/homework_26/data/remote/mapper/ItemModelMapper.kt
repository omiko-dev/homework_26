package com.example.homework_26.data.remote.mapper

import com.example.homework_26.data.remote.model.ItemDto
import com.example.homework_26.domain.model.ItemModel

fun ItemDto.toDomain(): ItemModel =
    ItemModel(
        id = id,
        name = name,
        nameDe = nameDe,
        main = main.toString(),
        children = children?.filterNot { it.id == id }?.map { it.toDomain() },
        createAt =createdAt,
        bglNumber = bglNumber,
        bglVariant = bglVariant.toString(),
        orderId = orderId
    )