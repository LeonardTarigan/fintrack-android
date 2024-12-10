package com.project.fintrack.domain.usecases

import com.project.fintrack.data.models.BalanceReport
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.data.models.TransactionType
import com.project.fintrack.domain.strategies.ReportStrategy

class BalanceCalculationStrategy : ReportStrategy<BalanceReport> {
    override fun generateReport(transactions: List<TransactionEntity>): BalanceReport {
        // Calculate total income and expense based on transaction type
        val income = transactions.filter { it.type == TransactionType.INCOME }.sumOf { it.amount }
        val expense = transactions.filter { it.type == TransactionType.EXPENSE }.sumOf { it.amount }

        // Calculate the balance as income minus expense
        val balance = income - expense

        return BalanceReport(income, expense, balance)
    }
}
