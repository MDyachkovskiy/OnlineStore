package com.test.application.core.repository

import com.test.application.core.domain.auth.UserLogin
import kotlinx.coroutines.flow.Flow

interface AccountProfileRepository {
    suspend fun getUserInfo() : Flow<UserLogin>
    suspend fun countFavorites(): Int
    suspend fun clearUserData()
}