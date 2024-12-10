package com.project.fintrack.domain.usecases

import com.project.fintrack.data.models.ChartData
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.domain.strategies.ReportStrategy
import androidx.compose.ui.graphics.Color
import com.project.fintrack.ui.theme.FinTrackGreen
import com.project.fintrack.ui.theme.FinTrackGrey
import com.project.fintrack.ui.theme.FinTrackPrimary
import com.project.fintrack.ui.theme.FinTrackRed
import com.project.fintrack.ui.theme.FinTrackYellow

class ChartReportStrategy : ReportStrategy<List<ChartData>> {

    override fun generateReport(transactions: List<TransactionEntity>): List<ChartData> {
        val categoryTotals = transactions.groupBy { it.category }
            .mapValues { entry -> entry.value.sumOf { it.amount } }

        val sortedCategories = categoryTotals.entries.sortedByDescending { it.value }

        val topCategories = sortedCategories.take(3)
        val otherCategories = sortedCategories.drop(3).sumOf { it.value }

        val totalAmount = transactions.sumOf { it.amount }

        val chartData = topCategories.mapIndexed { index, entry ->
            val percentage = (entry.value.toDouble() / totalAmount * 100).toInt()
            ChartData(
                label = entry.key.toString(),
                total = entry.value.toInt(),
                color = getCategoryColor(index),
                percentage = percentage
            )
        }

        val othersPercentage = (otherCategories.toDouble() / totalAmount * 100).toInt()
        val othersChartData = ChartData(
            label = "Others",
            total = otherCategories.toInt(),
            color = FinTrackRed,
            percentage = othersPercentage
        )

        return chartData + othersChartData
    }

    private fun getCategoryColor(index: Int): Color {
        return when (index) {
            0 -> FinTrackPrimary
            1 -> FinTrackYellow
            2 -> FinTrackGreen
            else -> FinTrackGrey
        }
    }
}
