package com.project.fintrack.presentation.viewmodels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.fintrack.data.models.ChartData
import com.project.fintrack.data.models.TransactionCategory
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.data.models.TransactionType
import com.project.fintrack.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

class HomeViewModel : ViewModel() {
//    val repository: Repository = Repository()

    private val _recentTransactions = MutableLiveData<List<TransactionEntity>>(emptyList())
    private val _chartData = MutableLiveData<List<ChartData>>(emptyList())

    val recentTransactions: LiveData<List<TransactionEntity>> = _recentTransactions
    val chartData: LiveData<List<ChartData>> = _chartData

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        viewModelScope.launch {
            _recentTransactions.value = listOf(
                TransactionEntity(
                    id = 1,
                    amount = 50000.0,
                    date = Date(2024, 11, 15), // November 15, 2024
                    description = "Shopping",
                    category = TransactionCategory.SHOPPING,
                    type = TransactionType.EXPENSE
                ),
                TransactionEntity(
                    id = 2,
                    amount = 30000.0,
                    date = Date(2024, 11, 15),
                    description = "Food",
                    category = TransactionCategory.FOOD,
                    type = TransactionType.EXPENSE
                ),
                TransactionEntity(
                    id = 3,
                    amount = 2000000.0,
                    date = Date(2024, 11, 14),
                    description = "Investment",
                    category = TransactionCategory.INVESTMENT,
                    type = TransactionType.EXPENSE
                ),
                TransactionEntity(
                    id = 4,
                    amount = 45000.0,
                    date = Date(2024, 11, 14),
                    description = "Entertainment",
                    category = TransactionCategory.ENTERTAINMENT,
                    type = TransactionType.EXPENSE
                ),
                TransactionEntity(
                    id = 5,
                    amount = 25000.0,
                    date = Date(2024, 11, 13),
                    description = "Food",
                    category = TransactionCategory.FOOD,
                    type = TransactionType.EXPENSE
                )
            )

            _chartData.value = listOf(
                ChartData("Food", 600000, Color.Red, 60),
                ChartData("Entertainment", 600000, Color.Red, 20),
                ChartData("Education", 600000, Color.Red, 10),
                ChartData("Others", 600000, Color.Red, 10),
            )
        }
    }
}
