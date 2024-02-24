package com.example.homework_26.presentation.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_26.data.common.Resource
import com.example.homework_26.domain.usecase.GetItemUseCase
import com.example.homework_26.presentation.mapper.toPresenter
import com.example.homework_26.presentation.screen.search.event.SearchEvent
import com.example.homework_26.presentation.state.ItemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel @Inject constructor(
    private val getItemUseCase: GetItemUseCase,
) : ViewModel() {
    private val _itemStateFlow = MutableStateFlow(ItemState())
    val itemStateFlow get() = _itemStateFlow.asStateFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.GetItem -> getItem(event.filter)
        }
    }

    private fun getItem(filterText: String?) {
        viewModelScope.launch {
            getItemUseCase(filterText).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _itemStateFlow.update { state ->
                            state.copy(
                                itemUiList = resource.success.map { it.toPresenter() }
                            )
                        }
                    }

                    is Resource.Error -> {
                        _itemStateFlow.update { state ->
                            state.copy(
                                error = resource.error
                            )
                        }
                    }

                    is Resource.Loader -> {
                        _itemStateFlow.update { state ->
                            state.copy(
                                loader = resource.loader
                            )
                        }
                    }
                }
            }
        }
    }
}