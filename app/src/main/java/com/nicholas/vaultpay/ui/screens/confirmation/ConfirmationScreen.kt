package com.nicholas.vaultpay.ui.screens.confirmation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ConfirmationScreen(
    navController: NavController,
    recipient: String,
    amount: String
) {
    // Content of the Confirmation Screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Transaction Successful",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF1A237E)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Transaction details
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0072FF)),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text("Amount Sent", color = Color.White.copy(alpha = 0.7f))
                    Text(
                        text = "Ksh $amount",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Recipient: $recipient",
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Back Button
        Button(
            onClick = {
                navController.popBackStack() // Navigate back to the HomeScreen or previous screen
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0072FF))
        ) {
            Text("Go to Home", style = MaterialTheme.typography.bodyLarge, color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConfirmationScreenPreview() {
    ConfirmationScreen(navController = rememberNavController(), recipient = "John Doe", amount = "500")
}
