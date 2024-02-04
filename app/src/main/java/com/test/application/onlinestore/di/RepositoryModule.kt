package com.test.application.onlinestore.di

import com.test.application.core.repository.AccountProfileRepository
import com.test.application.core.repository.AuthDataRepository
import com.test.application.core.repository.CatalogueInteractor
import com.test.application.core.repository.CatalogueInteractorImpl
import com.test.application.core.repository.FavouritesRepository
import com.test.application.core.repository.RemoteDataRepository
import com.test.application.core.repository.UserRepository
import com.test.application.local_data.repository.AccountProfileRepositoryImpl
import com.test.application.local_data.repository.AuthDataRepositoryImpl
import com.test.application.local_data.repository.FavouriteRepositoryImpl
import com.test.application.local_data.repository.UserRepositoryImpl
import com.test.application.remote_data.repository.RemoteDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAccountProfileRepository(
        repositoryImpl: AccountProfileRepositoryImpl
    ): AccountProfileRepository

    @Binds
    abstract fun bindUserRepository(
        repositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindAuthDataRepository(
        repositoryImpl: AuthDataRepositoryImpl
    ): AuthDataRepository

    @Binds
    abstract fun bindCatalogueInteractor(
        intercatorImpl: CatalogueInteractorImpl
    ): CatalogueInteractor

    @Binds
    abstract fun bindRemoteDataRepository(
        repositoryImpl: RemoteDataRepositoryImpl
    ): RemoteDataRepository

    @Binds
    abstract fun bindFavouritesRepository(
        repositoryImpl: FavouriteRepositoryImpl
    ): FavouritesRepository
}