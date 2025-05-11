package com.nicholas.vaultpay.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nicholas.vaultpay.R
import com.nicholas.vaultpay.navigation.ROUTE_NOTIFICATIONS
import com.nicholas.vaultpay.navigation.ROUTE_SUPPORT
import com.nicholas.vaultpay.navigation.ROUT_PAY_BILLS
import com.nicholas.vaultpay.navigation.ROUT_PROFILE
import com.nicholas.vaultpay.navigation.ROUT_SEND_MONEY
import com.nicholas.vaultpay.navigation.ROUT_TRANSACTIONS
import com.nicholas.vaultpay.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    username: String,
    homeViewModel: HomeViewModel = viewModel()
) {
    // Getting balance from ViewModel
    val balance by homeViewModel.balance
    val backgroundColor = Color(0xFF5C6BC0)
    val cardColor = Color(0xFFE1BEE7)
    val textColor = Color.White

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background_imagehome),
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Display Username
            Text(
                text = "Welcome, $username!",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = textColor
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Balance Card
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = cardColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
                        Text("Current Balance", color = textColor.copy(alpha = 0.7f), fontSize = 14.sp)
                        Text(
                            text = "KSh %.2f".format(balance),
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                            color = textColor
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Quick Actions
            Text(
                "Quick Actions",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                color = textColor
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    QuickActionButton("Send", Icons.Filled.Send, cardColor) {
                        navController.navigate(ROUT_SEND_MONEY)
                    }
                    QuickActionButton("Transactions", Icons.Filled.List, cardColor) {
                        navController.navigate(ROUT_TRANSACTIONS)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    QuickActionButton("Pay Bills", Icons.Filled.ShoppingCart, cardColor) {
                        navController.navigate(ROUT_PAY_BILLS)
                    }

                    QuickActionButton("Profile", Icons.Filled.AccountCircle, cardColor) {
                        navController.navigate(ROUT_PROFILE)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    QuickActionButton("Notifications", Icons.Filled.Notifications, cardColor) {
                        navController.navigate(ROUTE_NOTIFICATIONS)
                    }

                    QuickActionButton("Support", Icons.Filled.Call, cardColor) {
                        navController.navigate(ROUTE_SUPPORT)
                    }


                }
            }
        }
    }
}

@Composable
fun QuickActionButton(label: String, icon: ImageVector, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(140.dp)
            .clickable(onClick = onClick)
            .indication(interactionSource = remember { MutableInteractionSource() }, indication = LocalIndication.current),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(34.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(label, color = Color.White, fontWeight = FontWeight.Medium)
        }
    }
}
