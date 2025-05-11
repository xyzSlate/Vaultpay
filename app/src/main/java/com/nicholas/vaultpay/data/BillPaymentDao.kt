package com.nicholas.vaultpay.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow
import com.nicholas.vaultpay.model.BillPayment

@Dao
interface BillPaymentDao {

    @Insert
    suspend fun insert(payment: BillPayment)

    @Update
    suspend fun update(payment: BillPayment)

    @Delete
    suspend fun delete(payment: BillPayment)

    @Query("SELECT * FROM bill_payments")
    fun getAllPayments(): Flow<List<BillPayment>>

    @Query("SELECT * FROM bill_payments WHERE id = :paymentId")
    suspend fun getPaymentById(paymentId: Long): BillPayment?
}
