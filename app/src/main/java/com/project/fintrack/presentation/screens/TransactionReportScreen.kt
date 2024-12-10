package com.project.fintrack.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.fintrack.data.models.MonthlyTransactions
import com.project.fintrack.data.models.TopSpending
import com.project.fintrack.presentation.components.TopSpendingItem
import com.project.fintrack.presentation.components.TransactionItem
import com.project.fintrack.presentation.viewmodels.TransactionReportViewModel
import com.project.fintrack.ui.theme.FinTrackGreen
import com.project.fintrack.ui.theme.FinTrackGrey
import com.project.fintrack.ui.theme.FinTrackPrimary
import com.project.fintrack.ui.theme.FinTrackRed
import com.project.fintrack.utils.formatToRupiah

@Composable
fun ReportScreen(viewModel: TransactionReportViewModel) {
    val topSpendingData = viewModel.topSpendingData.value
    val monthlyTransactions = viewModel.monthlyTransactions.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 72.dp)
            .verticalScroll(rememberScrollState())
    ) {
        OverviewSection()
        if (topSpendingData != null) {
            TopSpendingSection(topSpendingData)
        }
        if (monthlyTransactions != null) {
            MonthlyTransactionsSection(monthlyTransactions)
        }
    }
}

@Composable
fun OverviewSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(color = FinTrackPrimary)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            Text(
                text = "Transaction Report",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 52.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Total Balance",
                    color = Color.White
                )
                Text(
                    text = formatToRupiah(1000000.0),
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
           Card(
               modifier = Modifier.weight(1f),
               elevation = CardDefaults.cardElevation(4.dp),
               shape = RoundedCornerShape(16.dp),
               colors = CardDefaults.cardColors(containerColor = Color.White)
           ){
               Column(
                   modifier = Modifier
                       .padding(16.dp)
                       .fillMaxWidth(),
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Text(
                       text = "Income",
                       color = FinTrackGreen,
                   )
                   Text(
                       text = formatToRupiah(1000000.0),
                       style = MaterialTheme.typography.titleMedium,
                       fontWeight = FontWeight.SemiBold
                   )
               }
           }
            Spacer(modifier = Modifier.width(16.dp))
            Card(
                modifier = Modifier.weight(1f),
                elevation = CardDefaults.cardElevation(4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ){
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Spending",
                        color = FinTrackRed
                    )
                    Text(
                        text = formatToRupiah(1000000.0),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun TopSpendingSection(data: List<TopSpending>) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Top Spending", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
        data.forEach { item ->
           TopSpendingItem(item)
        }
    }
}

@Composable
fun MonthlyTransactionsSection(data: List<MonthlyTransactions>) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = "Monthly Transactions", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
        data.forEach { monthlyItem ->
            Column(
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(
                    text = monthlyItem.month,
                    style = MaterialTheme.typography.titleMedium,
                    color = FinTrackGrey
                )
                monthlyItem.transactions.forEach {transaction ->
                    TransactionItem(transaction)
                }
            }
        }
    }
}