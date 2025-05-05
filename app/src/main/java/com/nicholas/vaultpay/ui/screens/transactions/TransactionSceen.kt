package com.nicholas.vaultpay.ui.screens.transactions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicholas.vaultpay.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nicholas.vaultpay.model.Transaction

@Composable
fun TransactionsScreen(navController: NavController) {
    // Sample transactions
    val transactions = listOf(
        Transaction("John Doe", "-Ksh 500", "Sent", "04 May 2025"),
        Transaction("Mpesa Top-up", "+Ksh 1,000", "Top-up", "03 May 2025"),
        Transaction("Jane K.", "-Ksh 200", "Sent", "02 May 2025")
    )
    val backgroundImage: Painter = painterResource(id = R.drawable.backgroundimage1)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Transaction History",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF1A237E)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Using LazyColumn to display a list of transactions
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(transactions) { transaction ->
                TransactionCard(transaction)
            }
        }
    }
}

@Composable
fun TransactionCard(transaction: Transaction) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = transaction.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF616161)
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = transaction.amount,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF0072FF)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionsScreenPreview() {
    TransactionsScreen(navController = rememberNavController())
}
