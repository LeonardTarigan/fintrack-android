package com.project.fintrack.utils

import androidx.room.TypeConverter
import com.project.fintrack.data.models.TransactionCategory
import com.project.fintrack.data.models.TransactionType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return value?.let {
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            format.parse(it)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): String? {
        return date?.let {
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            format.format(it)
        }
    }

    @TypeConverter
    fun fromTransactionCategory(value: String): TransactionCategory {
        return TransactionCategory.valueOf(value)
    }

    @TypeConverter
    fun toTransactionCategory(category: TransactionCategory): String {
        return category.name
    }

    @TypeConverter
    fun fromTransactionType(value: String): TransactionType {
        return TransactionType.valueOf(value)
    }

    @TypeConverter
    fun toTransactionType(type: TransactionType): String {
        return type.name
    }
}
