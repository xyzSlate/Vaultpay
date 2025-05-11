package com.nicholas.vaultpay.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // Auto-generated ID for Room
    val title: String,
    val amount: Double,
    val date: String,
    val recipientName: String,
    val accountNumber: String // New field to store the account number
)

