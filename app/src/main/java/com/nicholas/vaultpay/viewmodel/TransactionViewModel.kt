package com.nicholas.vaultpay.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.nicholas.vaultpay.data.TransactionDatabase
import com.nicholas.vaultpay.model.Transaction
import com.nicholas.vaultpay.repository.TransactionRepository
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TransactionRepository
    val allTransactions: LiveData<List<Transaction>>

    init {
        // Get DAO from the database and initialize the repository
        val dao = TransactionDatabase.getDatabase(application).TransactionDao()
        repository = TransactionRepository(dao)

        // Fetch all transactions from the repository
        allTransactions = repository.allTransactions
    }

    // Insert a new transaction into the database
    fun insert(transaction: Transaction) = viewModelScope.launch {
        repository.insert(transaction)
    }

    // Delete a transaction from the database
    fun delete(transaction: Transaction) = viewModelScope.launch {
        repository.delete(transaction)
    }
}
