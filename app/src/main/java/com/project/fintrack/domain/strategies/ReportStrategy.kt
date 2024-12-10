package com.project.fintrack.domain.strategies

import com.project.fintrack.data.models.TransactionEntity

interface ReportStrategy<T> {
    fun generateReport(transactions: List<TransactionEntity>): T
}