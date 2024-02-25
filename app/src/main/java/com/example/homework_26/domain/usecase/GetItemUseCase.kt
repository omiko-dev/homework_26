package com.example.homework_26.domain.usecase

import com.example.homework_26.data.common.Resource
import com.example.homework_26.domain.model.ItemModel
import com.example.homework_26.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetItemUseCase @Inject constructor(
    private val itemRepository: ItemRepository,
) {
    suspend operator fun invoke(filterName: String?): Flow<Resource<List<ItemModel>>> {
        return if (filterName.isNullOrEmpty()) {
            itemRepository.getItems()
        } else {
            itemRepository.getItems().map { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val filteredItems = resource.success.filter { it.name.lowercase().contains(filterName.lowercase().trim()) }
                        Resource.Success(success = filteredItems)
                    }

                    is Resource.Loader -> Resource.Loader(loader = resource.loader)
                    is Resource.Error -> Resource.Error(error = resource.error)
                }
            }.filterNotNull()
        }
    }
}