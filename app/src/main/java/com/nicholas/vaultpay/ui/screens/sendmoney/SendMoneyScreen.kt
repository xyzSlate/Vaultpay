package com.nicholas.vaultpay.ui.screens.sendmoney

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


class SendMoneyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SendMoneyScreen(navController = rememberNavController())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMoneyScreen(navController: NavController) {
    // States for text fields
    var recipientName = remember { TextFieldValue() }
    var amount = remember { TextFieldValue() }

    // Content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Send Money",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF1A237E)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Recipient name field
        TextField(
            value = recipientName,
            onValueChange = { recipientName = it },
            label = { Text("Recipient Name") },
            placeholder = { Text("Enter recipient's name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            )
        )

        // Amount field
        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            placeholder = { Text("Enter amount to send") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            )
        )

        // Send Button
        Button(
            onClick = {
                val recipient = recipientName.text.trim()
                val amountText = amount.text.trim()

                if (recipient.isEmpty() || amountText.isEmpty()) {
                    Toast.makeText(navController.context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                } else {
                    sendMoney(navController, recipient, amountText)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0072FF))
        ) {
            Text("Send Money", style = MaterialTheme.typography.bodyLarge, color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Back Button (to navigate back to HomeScreen)
        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xFF0072FF),
                containerColor = Color.White
            )
        ) {
            Text("Back", style = MaterialTheme.typography.bodyLarge)
        }
    }
}


fun sendMoney(navController: NavController, recipient: String, amount: String) {
    navController.navigate("confirmation_screen/$recipient/$amount")
    // Show a toast indicating money is being sent
    Toast.makeText(navController.context, "Sending Ksh $amount to $recipient", Toast.LENGTH_LONG).show()

}
