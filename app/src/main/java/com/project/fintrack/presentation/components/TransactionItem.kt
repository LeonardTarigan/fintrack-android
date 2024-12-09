package com.project.fintrack.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.data.models.TransactionType
import com.project.fintrack.ui.theme.FinTrackGreen
import com.project.fintrack.ui.theme.FinTrackRed
import com.project.fintrack.utils.formatDate
import com.project.fintrack.utils.formatToRupiah
import com.project.fintrack.utils.getCategoryColor

@Composable
fun TransactionItem(transaction: TransactionEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val amountText = if (transaction.type == TransactionType.INCOME) {
            "+ ${formatToRupiah(transaction.amount)}"
        } else {
            "- ${formatToRupiah(transaction.amount)}"
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box( modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(color = getCategoryColor(transaction.category))
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = transaction.category.toString(), fontWeight = FontWeight.Bold)
                Text(text = formatDate(transaction.date), style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
        Text(
            text = amountText,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = if (transaction.type == TransactionType.INCOME) FinTrackGreen else FinTrackRed
        )
    }
}