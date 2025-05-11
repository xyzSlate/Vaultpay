package com.nicholas.vaultpay.ui.screens.transactions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nicholas.vaultpay.R
import com.nicholas.vaultpay.model.Transaction
import com.nicholas.vaultpay.viewmodel.TransactionViewModel
import kotlin.math.abs

@Composable
fun TransactionsScreen(
    navController: NavController,
    viewModel: TransactionViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        val date = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())

        // Sent Money example
        val sentMoneyTransaction = Transaction(
            title = "Sent to Jane",
            amount = -500.0,
            date = date,
            recipientName = "Jane",
            accountNumber = "12345678"
        )
        viewModel.insert(sentMoneyTransaction)

        // Bill Payment example
        val billPaymentTransaction = Transaction(
            title = "Paid Electricity Bill",
            amount = -2000.0,
            date = date,
            recipientName = "Kenya Power",
            accountNumber = "KPLC-ACC-8899"
        )
        viewModel.insert(billPaymentTransaction)
    }

    val transactions by viewModel.allTransactions.observeAsState(initial = emptyList())
    val backgroundColor = Color(0xFF5C6BC0) // Soft purple background

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.backgroundimage1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(backgroundColor.copy(alpha = 0.7f), Color.Black.copy(alpha = 0.4f))
                    )
                )
        )
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Transaction History",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(transactions) { transaction ->
                    TransactionCard(transaction)
                }
            }
        }
    }
}

@Composable
fun TransactionCard(transaction: Transaction) {
    val isPositive = transaction.amount >= 0
    val amountColor = if (isPositive) Color(0xFF8E24AA) else Color(0xFFD81B60)
    val symbol = if (isPositive) "+" else "-"

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3E5F5)),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp), // Increased height to fit the new content
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = transaction.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF4A148C),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = transaction.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$symbol Ksh ${abs(transaction.amount)}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = amountColor,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Account: ${transaction.accountNumber}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}
