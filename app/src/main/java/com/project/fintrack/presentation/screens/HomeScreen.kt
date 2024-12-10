package com.project.fintrack.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.project.fintrack.data.models.ChartData
import com.project.fintrack.data.models.TransactionCategory
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.data.models.TransactionType
import com.project.fintrack.presentation.components.TransactionItem
import com.project.fintrack.presentation.viewmodels.HomeViewModel
import com.project.fintrack.ui.theme.FinTrackGreen
import com.project.fintrack.ui.theme.FinTrackPrimary
import com.project.fintrack.ui.theme.FinTrackRed
import com.project.fintrack.utils.formatDate
import com.project.fintrack.utils.formatToRupiah
import com.project.fintrack.utils.getCategoryColor
import java.util.Date


@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val recentTransactions = viewModel.recentTransactions.value
    val chartData = viewModel.chartData.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 52.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderSection()
        if (chartData != null) {
            ChartSection(chartData)
        }
        if (recentTransactions != null) {
            RecentTransactionsSection(recentTransactions)
        }
    }
}

@Composable
fun HeaderSection() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .background(color = FinTrackPrimary),
        horizontalArrangement = Arrangement.SpaceBetween,

    ) {
        Column(
            modifier = Modifier.
                padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 152.dp)
        ) {
            Text(
                text = "Good Morning,",
                color = Color.White
            )
            Text(
                text = "Fin Friend",
                fontWeight = FontWeight.Bold,
                color = Color.White, style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(text = "Income", color = Color.White)
                    Text(text = "Rp 3,000,000", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(text = "Spending", color = Color.White)
                    Text(text = "Rp 1,000,000", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "User Avatar",
            modifier = Modifier.size(48.dp),
            tint = Color.Gray
        )
    }
}

@Composable
fun ChartSection(data: List<ChartData>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .offset(y = (-112).dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
           Column(
               modifier = Modifier.weight(1f)
           ) {
               Text(text = "")
           }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                data.forEachIndexed() { index, transaction ->
                    Row(
                        modifier = Modifier.padding(top = if(index  > 0) 8.dp else 0.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column() {
                            Text(text = transaction.label, style = MaterialTheme.typography.bodySmall)
                            Text(text= formatToRupiah(transaction.total.toDouble()), style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
                        }

                        Text(text = "${transaction.percentage}%", style = MaterialTheme.typography.bodySmall, textAlign = TextAlign.End)
                    }
                }
            }
        }
    }
}

@Composable
fun RecentTransactionsSection(transactions: List<TransactionEntity>) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .offset(y = (-72).dp),
    ) {
        Text(text = "Recent Cash Flow", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
        transactions.forEach { transaction ->
            TransactionItem(transaction = transaction)
        }
    }
}
