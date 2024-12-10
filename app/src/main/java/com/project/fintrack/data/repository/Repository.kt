package com.project.fintrack.data.repository

import com.project.fintrack.data.db.LocalDatabase


class Repository(private val db: LocalDatabase) {

    fun getAllTransactions() = db.transactionDao().getAll()

}
