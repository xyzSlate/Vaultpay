package com.nicholas.vaultpay.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nicholas.vaultpay.navigation.ROUT_SEND_MONEY
import com.nicholas.vaultpay.navigation.ROUT_TRANSACTIONS


@Composable
fun HomeScreen(
    navController: NavController,
    userName: String ,
    balance: String = "KSh 12,350.00"
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Hello, $userName!",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF1A237E)
        )

        Spacer(modifier = Modifier.height(16.dp))

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
                    Text("Current Balance", color = Color.Blue.copy(alpha = 0.7f))
                    Text(
                        text = balance,
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Quick Actions", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(12.dp))

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                QuickActionButton("Send", Icons.Filled.Send) {
                    navController.navigate(ROUT_SEND_MONEY)
                }
                QuickActionButton("Transactions", Icons.Filled.List) {
                    navController.navigate(ROUT_TRANSACTIONS)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                QuickActionButton("Pay Bills", Icons.Filled.ShoppingCart) {
                    navController.navigate("pay_bills")
                }
                QuickActionButton("Profile", Icons.Filled.AccountCircle) {
                    navController.navigate("profile")
                }
            }
        }
    }


}
@Composable
fun QuickActionButton(label: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = label, tint = Color(0xFF0072FF), modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(label, color = Color(0xFF1A237E))
        }
    }
}


