package com.project.fintrack.domain.usecases

import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.domain.strategies.ReportStrategy

class RecentTransactionStrategy : ReportStrategy<List<TransactionEntity>> {
    override fun generateReport(transactions: List<TransactionEntity>): List<TransactionEntity> {
        return transactions.take(10)
    }
}