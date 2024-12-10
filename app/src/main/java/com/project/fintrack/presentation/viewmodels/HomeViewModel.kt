package com.project.fintrack.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.fintrack.data.models.BalanceReport
import com.project.fintrack.data.models.ChartData
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.data.repository.Repository
import com.project.fintrack.domain.strategies.ReportStrategy
import com.project.fintrack.domain.usecases.BalanceCalculationStrategy
import com.project.fintrack.domain.usecases.ChartReportStrategy
import com.project.fintrack.domain.usecases.RecentTransactionStrategy
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private var reportStrategy: ReportStrategy<*>? = null

    private val _recentTransactions = MutableLiveData<List<TransactionEntity>>(emptyList())
    private val _chartData = MutableLiveData<List<ChartData>>(emptyList())
    private val _balanceReport = MutableLiveData<BalanceReport>()

    val recentTransactions: LiveData<List<TransactionEntity>> get() = _recentTransactions
    val chartData: LiveData<List<ChartData>> get() = _chartData
    val balanceReport: LiveData<BalanceReport> get() = _balanceReport

    init {
        getReportData()
    }

    private fun getReportData() {
        viewModelScope.launch {
            val transactions = repository.getAllTransactions()

            setStrategy(RecentTransactionStrategy())
            _recentTransactions.value = executeStrategy<List<TransactionEntity>>(transactions)

            setStrategy(BalanceCalculationStrategy())
            _balanceReport.value = executeStrategy<BalanceReport>(transactions)

            setStrategy(ChartReportStrategy())
            _chartData.value = executeStrategy<List<ChartData>>(transactions)
        }
    }

    private fun setStrategy(strategy: ReportStrategy<*>) {
        reportStrategy = strategy
    }

    private fun <T> executeStrategy(transactions: List<TransactionEntity>): T? {
        return reportStrategy?.generateReport(transactions) as? T
    }
}
