package com.tengizMKCorp.tengizExpress.di

import com.tengizMKCorp.tengizExpress.data.repository.StoreRepositoryImpl
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        myRepositoryImpl: StoreRepositoryImpl
    ): StoreRepository
}