package com.test.application.local_data.user_info

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserInfoDao {

    @Query("SELECT * FROM UserInfoEntity")
    fun getAllUsers() : List<UserInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfo(entity: UserInfoEntity)

    @Delete
    fun delete (entity: UserInfoEntity)
}