package com.test.application.local_data.user_info

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfoEntity (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var name: String,
    var secondName: String,
    var phoneNumber: String,
    var isLogged: Boolean = true
)