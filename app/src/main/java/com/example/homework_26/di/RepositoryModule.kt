package com.example.homework_26.di

import com.example.homework_26.data.remote.service.ItemService
import com.example.homework_26.data.repository.ItemRepositoryImpl
import com.example.homework_26.domain.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideItemRepository(
        itemService: ItemService,
    ): ItemRepository =
        ItemRepositoryImpl(
            itemService = itemService
        )
}