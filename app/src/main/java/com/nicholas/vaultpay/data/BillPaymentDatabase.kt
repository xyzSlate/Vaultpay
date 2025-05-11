package com.nicholas.vaultpay.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nicholas.vaultpay.model.BillPayment
import com.nicholas.vaultpay.dao.BillPaymentDao

@Database(entities = [BillPayment::class], version = 1, exportSchema = false)
abstract class BillPaymentDatabase : RoomDatabase() {

    abstract fun billPaymentDao(): BillPaymentDao

    companion object {
        @Volatile
        private var INSTANCE: BillPaymentDatabase? = null

        fun getDatabase(context: Context): BillPaymentDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BillPaymentDatabase::class.java,
                    "bill_payment_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


