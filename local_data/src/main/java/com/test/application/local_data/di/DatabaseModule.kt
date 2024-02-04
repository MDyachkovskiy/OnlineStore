package com.test.application.local_data.di

import android.content.Context
import androidx.room.Room
import com.test.application.local_data.database.LocalDatabase
import com.test.application.local_data.favourite_item.ProductDao
import com.test.application.local_data.user_info.UserInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            "UserLoginDatabase.db"
        ).build()
    }

    @Provides
    fun provideUserInfo(database: LocalDatabase): UserInfoDao {
        return database.userInfoDao()
    }

    @Provides
    fun provideProductDao(database: LocalDatabase): ProductDao {
        return database.productDao()
    }
}