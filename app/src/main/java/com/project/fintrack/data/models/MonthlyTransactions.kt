package com.project.fintrack.data.models

data class MonthlyTransactions(
    val month: String,
    val transactions: List<TransactionEntity>
)
