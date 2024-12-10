package com.project.fintrack.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.fintrack.data.models.MonthlyTransactions
import com.project.fintrack.data.models.TopSpending
import com.project.fintrack.data.models.TransactionCategory
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.data.models.TransactionType
import com.project.fintrack.data.repository.Repository
import kotlinx.coroutines.launch
import java.util.Date

class TransactionReportViewModel(private val repository: Repository) : ViewModel() {

    private val _topSpendingData = MutableLiveData<List<TopSpending>>(emptyList())
    private val _monthlyTransactions = MutableLiveData<List<MonthlyTransactions>>(emptyList())

    val topSpendingData: LiveData<List<TopSpending>> = _topSpendingData
    val monthlyTransactions: LiveData<List<MonthlyTransactions>> = _monthlyTransactions

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        viewModelScope.launch {
            _topSpendingData.value = listOf(
                TopSpending(TransactionCategory.ENTERTAINMENT, 45.73),
                TopSpending(TransactionCategory.FOOD, 37.29),
                TopSpending(TransactionCategory.SHOPPING, 21.4),
                TopSpending(TransactionCategory.EDUCATION, 17.95),
                TopSpending(TransactionCategory.TRANSPORTATION, 14.33),
            )

            _monthlyTransactions.value = listOf(
                MonthlyTransactions("November", listOf(
                    TransactionEntity(
                        id = 1,
                        amount = 50000.0,
                        date = Date(2024, 11, 15),
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
                )),
                MonthlyTransactions("October", listOf(
                    TransactionEntity(
                        id = 3,
                        amount = 50000.0,
                        date = Date(2024, 11, 15),
                        description = "Shopping",
                        category = TransactionCategory.SHOPPING,
                        type = TransactionType.EXPENSE
                    ),
                    TransactionEntity(
                        id = 4,
                        amount = 30000.0,
                        date = Date(2024, 11, 15),
                        description = "Food",
                        category = TransactionCategory.FOOD,
                        type = TransactionType.EXPENSE
                    ),
                )),
            )
        }
    }
}