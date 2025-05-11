package com.nicholas.vaultpay.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val balance = mutableStateOf(32350.00)

    fun updateBalance(amountPaid: Double) {
        if (amountPaid > 0 && amountPaid <= balance.value) {
            balance.value -= amountPaid
        }
    }
}
