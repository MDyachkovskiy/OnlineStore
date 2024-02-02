package com.test.application.local_data.mapper

import com.test.application.core.domain.auth.UserLogin
import com.test.application.local_data.user_info.UserInfoEntity

fun UserLogin.toEntity(): UserInfoEntity {
    return UserInfoEntity(
        id = this.id,
        name = this.name,
        secondName = this.secondName,
        phoneNumber = this.phoneNumber
    )
}

fun UserInfoEntity.toDomain(): UserLogin {
    return UserLogin(
        id = this.id,
        name = this.name,
        secondName = this.secondName,
        phoneNumber = this.phoneNumber
    )
}