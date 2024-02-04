package com.test.application.local_data.mapper

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromIntListToString(value: List<Int>?): String? {
        return value?.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromStringToIntList(value: String?): List<Int>? {
        return value?.split(",")?.map { it.toInt() }
    }
}