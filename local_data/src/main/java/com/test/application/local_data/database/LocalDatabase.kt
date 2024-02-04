package com.test.application.local_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.application.local_data.favourite_item.FeedbackEntity
import com.test.application.local_data.favourite_item.InfoEntity
import com.test.application.local_data.favourite_item.PriceEntity
import com.test.application.local_data.favourite_item.ProductDao
import com.test.application.local_data.favourite_item.ProductEntity
import com.test.application.local_data.mapper.Converters
import com.test.application.local_data.user_info.UserInfoDao
import com.test.application.local_data.user_info.UserInfoEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        UserInfoEntity::class, FeedbackEntity::class, InfoEntity::class, PriceEntity::class, ProductEntity::class
    ]
)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userInfoDao(): UserInfoDao
    abstract fun productDao(): ProductDao
}