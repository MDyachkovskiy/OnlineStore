package com.test.application.local_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.application.local_data.user_info.UserInfoDao
import com.test.application.local_data.user_info.UserInfoEntity

@Database(version = 1,
    exportSchema = false,
    entities = [
        UserInfoEntity ::class
    ]
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userInfoDao() : UserInfoDao
}