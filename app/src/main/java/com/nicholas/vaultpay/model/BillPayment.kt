package com.nicholas.vaultpay.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bill_payments")
data class BillPayment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val billType: String,
    val accountNumber: String,
    val amount: Double,
    val dueDate: String,
)
