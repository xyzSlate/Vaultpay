package com.nicholas.vaultpay.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nicholas.vaultpay.model.BillPayment
import com.nicholas.vaultpay.repository.BillPaymentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BillPaymentViewModel(
    private val repository: BillPaymentRepository
) : ViewModel() {

    val allPayments: StateFlow<List<BillPayment>> =
        repository.allPayments.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insertPayment(payment: BillPayment) = viewModelScope.launch {
        repository.insert(payment)
    }

    fun deletePayment(payment: BillPayment) = viewModelScope.launch {
        repository.delete(payment)
    }

    fun updatePayment(payment: BillPayment) = viewModelScope.launch {
        repository.update(payment)
    }
}
