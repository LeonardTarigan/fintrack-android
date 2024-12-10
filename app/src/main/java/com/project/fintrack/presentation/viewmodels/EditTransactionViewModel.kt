package com.project.fintrack.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.fintrack.data.models.TransactionEntity
import com.project.fintrack.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTransactionViewModel(private val repository: Repository) : ViewModel() {


    public fun editTransactions(transactionEntity: TransactionEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTransaction(transactionEntity)
        }
    }
    private val _transaction = mutableStateOf<TransactionEntity?>(null)
    val transaction: State<TransactionEntity?> = _transaction
    public fun getTransaction(id: Int){
        viewModelScope.launch(Dispatchers.IO){
            _transaction.value = repository.getTransaction(id)
        }
    }
}