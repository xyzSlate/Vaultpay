package com.nicholas.vaultpay.repository

import androidx.lifecycle.LiveData
import com.nicholas.vaultpay.data.TransactionDao
import com.nicholas.vaultpay.model.Transaction

class TransactionRepository(private val transactionDao: TransactionDao) {

    // Get all transactions
    val allTransactions: LiveData<List<Transaction>> = transactionDao.getAllTransactions()

    // Insert a new transaction
    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    // Delete a transaction
    suspend fun delete(transaction: Transaction) {
        transactionDao.delete(transaction)
    }
}
