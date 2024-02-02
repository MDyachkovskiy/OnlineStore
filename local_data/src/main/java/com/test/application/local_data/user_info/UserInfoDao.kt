package com.test.application.local_data.user_info

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserInfoDao {

    @Query("SELECT * FROM UserInfoEntity WHERE isLogged = 1 LIMIT 1")
    suspend fun getLoggedInUser(): UserInfoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfo(entity: UserInfoEntity)

    @Delete
    fun delete (entity: UserInfoEntity)
}