package com.project.fintrack.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.fintrack.data.models.BalanceReport
import com.project.fintrack.data.models.MonthlyTransactions
import com.project.fintrack.data.models.TopSpending
import com.project.fintrack.data.models.TransactionCategory
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.data.models.TransactionType
import com.project.fintrack.data.repository.Repository
import com.project.fintrack.domain.strategies.ReportStrategy
import com.project.fintrack.domain.usecases.BalanceCalculationStrategy
import com.project.fintrack.domain.usecases.MonthlySpendingStrategy
import com.project.fintrack.domain.usecases.TopSpendingStrategy
import kotlinx.coroutines.launch
import java.util.Date

class TransactionReportViewModel(private val repository: Repository) : ViewModel() {

    private var reportStrategy: ReportStrategy<*>? = null

    private val _balanceReport = MutableLiveData<BalanceReport>()
    private val _topSpendingData = MutableLiveData<List<TopSpending>>(emptyList())
    private val _monthlyTransactions = MutableLiveData<List<MonthlyTransactions>>(emptyList())

    val balanceReport: LiveData<BalanceReport> get() = _balanceReport
    val topSpendingData: LiveData<List<TopSpending>> get() = _topSpendingData
    val monthlyTransactions: LiveData<List<MonthlyTransactions>> get() = _monthlyTransactions

    init {
        getReportData()
    }

    private fun getReportData() {
        viewModelScope.launch {
            val transactions = repository.getAllTransactions()

            setStrategy(BalanceCalculationStrategy())
            _balanceReport.value = executeStrategy<BalanceReport>(transactions)

            setStrategy(TopSpendingStrategy())
            _topSpendingData.value = executeStrategy<List<TopSpending>>(transactions)

            setStrategy(MonthlySpendingStrategy())
            _monthlyTransactions.value = executeStrategy<List<MonthlyTransactions>>(transactions)
        }
    }

    private fun setStrategy(strategy: ReportStrategy<*>) {
        reportStrategy = strategy
    }

    private fun <T> executeStrategy(transactions: List<TransactionEntity>): T? {
        return reportStrategy?.generateReport(transactions) as? T
    }
}