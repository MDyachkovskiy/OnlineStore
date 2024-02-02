package com.test.application.local_data.user_info

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfoEntity (
    @PrimaryKey
    var id: Int,
    var name: String,
    var secondName: String,
    var phoneNumber: String
)