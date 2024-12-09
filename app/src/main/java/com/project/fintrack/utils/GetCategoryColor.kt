package com.project.fintrack.utils

import androidx.compose.ui.graphics.Color
import com.project.fintrack.data.models.TransactionCategory
import com.project.fintrack.ui.theme.FinTrackPrimary
import com.project.fintrack.ui.theme.FinTrackGreen
import com.project.fintrack.ui.theme.FinTrackRed
import com.project.fintrack.ui.theme.FinTrackYellow

fun getCategoryColor(category: TransactionCategory): Color {
    return when (category) {
        TransactionCategory.FOOD,
        TransactionCategory.TRANSPORTATION,
        TransactionCategory.HEALTH -> FinTrackPrimary

        TransactionCategory.SALARY,
        TransactionCategory.INVESTMENT,
        TransactionCategory.EDUCATION -> FinTrackGreen

        TransactionCategory.SHOPPING,
        TransactionCategory.TRAVEL -> FinTrackRed

        TransactionCategory.UTILITIES,
        TransactionCategory.HOUSING,
        TransactionCategory.ENTERTAINMENT -> FinTrackYellow
    }
}
