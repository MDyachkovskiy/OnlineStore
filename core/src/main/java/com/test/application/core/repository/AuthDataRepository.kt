package com.test.application.core.repository

import com.test.application.core.domain.UserLogin

interface AuthDataRepository {
    suspend fun saveAuthData(userInfo: UserLogin )
}