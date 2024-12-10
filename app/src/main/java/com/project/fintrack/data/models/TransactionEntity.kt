package com.project.fintrack.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "transactions")
data class TransactionEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Double,
    val date: Date,
    val description: String,
    val category: TransactionCategory,
    val type: TransactionType
)
