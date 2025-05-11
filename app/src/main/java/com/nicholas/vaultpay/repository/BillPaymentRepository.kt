package com.nicholas.vaultpay.repository

import com.nicholas.vaultpay.dao.BillPaymentDao
import com.nicholas.vaultpay.model.BillPayment
import kotlinx.coroutines.flow.Flow

class BillPaymentRepository(private val billPaymentDao: BillPaymentDao) {

    val allPayments: Flow<List<BillPayment>> = billPaymentDao.getAllPayments()

    suspend fun insert(payment: BillPayment) {
        billPaymentDao.insert(payment)
    }

    suspend fun delete(payment: BillPayment) {
        billPaymentDao.delete(payment)
    }

    suspend fun update(payment: BillPayment) {
        billPaymentDao.update(payment)
    }
}
