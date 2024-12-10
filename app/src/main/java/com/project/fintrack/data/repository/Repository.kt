package com.project.fintrack.data.repository

import com.project.fintrack.data.db.LocalDatabase
import com.project.fintrack.data.models.TransactionEntity


class Repository(private val db: LocalDatabase) {

    fun getAllTransactions() = db.transactionDao().getAll()
    fun createTransaction(transaction: TransactionEntity) = db.transactionDao().insert(transaction)
    fun updateTransaction(transaction: TransactionEntity) = db.transactionDao().update(transaction)
    fun getTransaction(id: Int) = db.transactionDao().getTransaction(id)
    fun deleteTransaction(transaction: TransactionEntity) = db.transactionDao().delete(transaction)
}
