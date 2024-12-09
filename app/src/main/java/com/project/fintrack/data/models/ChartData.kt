package com.project.fintrack.data.models

import androidx.compose.ui.graphics.Color

data class ChartData(
    val label: String,
    val total: Int,
    val color: Color,
    val percentage: Int
)
