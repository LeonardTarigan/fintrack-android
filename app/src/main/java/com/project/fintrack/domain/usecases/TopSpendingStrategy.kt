package com.project.fintrack.domain.usecases

import com.project.fintrack.data.models.TopSpending
import com.project.fintrack.data.models.TransactionCategory
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.data.models.TransactionType
import com.project.fintrack.domain.strategies.ReportStrategy
import kotlin.math.absoluteValue

class TopSpendingStrategy : ReportStrategy<List<TopSpending>> {
    override fun generateReport(transactions: List<TransactionEntity>): List<TopSpending> {
        println("Before Calc : ${transactions.toString()}")

        val categoryTotals = transactions
            .filter { it.type == TransactionType.EXPENSE }
            .groupBy { it.category }
            .mapValues { (_, transactions) ->
                transactions.sumOf { it.amount.absoluteValue }
            }

        println("Category Calc : ${categoryTotals.toString()}")

        val totalSpending = categoryTotals.values.sum()

        val spendingList = categoryTotals.map { (category, totalAmount) ->
            val percentage = (totalAmount / totalSpending) * 100
            val roundedPercentage = "%.2f".format(percentage).toDouble()

            TopSpending(
                category = category,
                percentage = roundedPercentage
            )
        }


        return spendingList
            .sortedByDescending { it.percentage }
            .take(5)
    }
}
