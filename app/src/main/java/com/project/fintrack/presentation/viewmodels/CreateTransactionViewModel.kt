package com.project.fintrack.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateTransactionViewModel(private val repository: Repository) : ViewModel() {

    public fun createTransactions(transactionEntity: TransactionEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createTransaction(transactionEntity)
        }
    }
}