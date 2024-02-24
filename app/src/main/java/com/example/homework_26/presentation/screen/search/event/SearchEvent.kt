package com.example.homework_26.presentation.screen.search.event

sealed class SearchEvent {
    data class GetItem(val filter: String? = null): SearchEvent()
}