package com.example.homework_26.presentation.state

import com.example.homework_26.presentation.model.ItemUi

data class ItemState (
    val itemUiList: List<ItemUi>? = null,
    val error: String? = null,
    val loader: Boolean = false
)