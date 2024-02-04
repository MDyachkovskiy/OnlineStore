package com.test.application.local_data.repository

import com.test.application.core.repository.UserRepository
import com.test.application.local_data.user_info.UserInfoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userInfoDao: UserInfoDao
) : UserRepository {
    override fun isUserLoggedIn(): Flow<Boolean> = flow {
        val user = userInfoDao.getLoggedInUser()
        emit(user != null)
    }.flowOn(Dispatchers.IO)
}