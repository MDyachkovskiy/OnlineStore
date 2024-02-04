package com.test.application.core.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun isUserLoggedIn(): Flow<Boolean>
}