package com.project.fintrack.domain.usecases

import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*
import com.project.fintrack.data.models.MonthlyTransactions
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.domain.strategies.ReportStrategy

class MonthlySpendingStrategy : ReportStrategy<List<MonthlyTransactions>> {
    override fun generateReport(transactions: List<TransactionEntity>): List<MonthlyTransactions> {
        val sdf = SimpleDateFormat("yyyy-MM", Locale.getDefault())

        val monthlyGroups = transactions
            .groupBy { sdf.format(it.date) }

        val monthlyTransactions = monthlyGroups.map { (month, transactions) ->
            MonthlyTransactions(
                month = month,
                transactions = transactions
            )
        }

        return monthlyTransactions
            .sortedByDescending {
                YearMonth.parse(it.month)
            }
            .map { monthlyTransaction ->
                val formattedMonth = YearMonth.parse(monthlyTransaction.month)
                    .format(DateTimeFormatter.ofPattern("MMM yyyy"))

                monthlyTransaction.copy(month = formattedMonth)
            }
    }
}