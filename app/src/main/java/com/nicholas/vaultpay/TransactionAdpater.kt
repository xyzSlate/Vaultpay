package com.nicholas.vaultpay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nicholas.vaultpay.model.Transaction

class TransactionAdapter(private val transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.textName)
        val amount: TextView = itemView.findViewById(R.id.textAmount)
        val type: TextView = itemView.findViewById(R.id.textType)
        val date: TextView = itemView.findViewById(R.id.textDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.name.text = transaction.name
        holder.amount.text = transaction.amount
        holder.type.text = transaction.type
        holder.date.text = transaction.date
    }

    override fun getItemCount(): Int = transactions.size
}
