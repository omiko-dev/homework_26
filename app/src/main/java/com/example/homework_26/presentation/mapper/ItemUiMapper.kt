package com.example.homework_26.presentation.mapper

import com.example.homework_26.data.remote.mapper.toDomain
import com.example.homework_26.domain.model.ItemModel
import com.example.homework_26.presentation.model.ItemUi

fun ItemModel.toPresenter(): ItemUi =
    ItemUi(
        id = id,
        name = name,
        nameDe = nameDe,
        main = main,
        children = children?.filterNot { it.id == id }?.map { it.toPresenter() },
        createAt = createAt,
        bglNumber = bglNumber,
        bglVariant = bglVariant,
        orderId = orderId
    )