package com.test.application.local_data.repository

import com.test.application.core.domain.auth.UserLogin
import com.test.application.core.repository.AuthDataRepository
import com.test.application.local_data.mapper.toEntity
import com.test.application.local_data.user_info.UserInfoDao
import javax.inject.Inject

class AuthDataRepositoryImpl @Inject constructor(
    private val userInfoDao: UserInfoDao
) : AuthDataRepository {
    override suspend fun saveAuthData(userInfo: UserLogin) {
        userInfoDao.insertUserInfo(userInfo.toEntity())
    }
}