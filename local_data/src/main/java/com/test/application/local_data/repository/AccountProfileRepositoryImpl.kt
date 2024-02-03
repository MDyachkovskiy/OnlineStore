package com.test.application.local_data.repository

import com.test.application.core.domain.auth.UserLogin
import com.test.application.core.repository.AccountProfileRepository
import com.test.application.local_data.favourite_item.ProductDao
import com.test.application.local_data.mapper.toDomain
import com.test.application.local_data.user_info.UserInfoDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccountProfileRepositoryImpl @Inject constructor(
    private val userInfoDao: UserInfoDao,
    private val productDao: ProductDao
) : AccountProfileRepository {
    override suspend fun getUserInfo(): Flow<UserLogin> {
        return flow {
            val userEntity = userInfoDao.getLoggedInUser()
            userEntity?.let { emit(it.toDomain()) }
        }
    }

    override suspend fun countFavorites(): Int = productDao.countFavorites()
    override suspend fun clearUserData() {
        userInfoDao.deleteAllUsers()
    }
}