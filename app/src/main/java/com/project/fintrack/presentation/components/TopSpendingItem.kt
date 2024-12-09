package com.project.fintrack.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.fintrack.data.models.TopSpending
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.data.models.TransactionType
import com.project.fintrack.ui.theme.FinTrackGreen
import com.project.fintrack.ui.theme.FinTrackGrey
import com.project.fintrack.ui.theme.FinTrackLightGrey
import com.project.fintrack.ui.theme.FinTrackPrimary
import com.project.fintrack.ui.theme.FinTrackRed
import com.project.fintrack.utils.formatDate
import com.project.fintrack.utils.formatToRupiah
import com.project.fintrack.utils.getCategoryColor

@Composable
fun TopSpendingItem(item: TopSpending) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box( modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(color = getCategoryColor(item.category))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.category.toString(),
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${item.percentage}%",
                        color = FinTrackGrey,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium
                    )
                }
                Spacer(
                    modifier = Modifier.height(4.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)

                        .clip(RoundedCornerShape(4.dp))
                        .background(FinTrackLightGrey)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(item.percentage.toFloat() / 100f)
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(FinTrackPrimary)
                    )
                }
            }
        }

    }
}