
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nicholas.vaultpay.model.Transaction
import com.nicholas.vaultpay.navigation.ROUT_CONFIRM
import com.nicholas.vaultpay.viewmodel.TransactionViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendMoneyScreen(navController: NavController) {
    // Get the ViewModel
    val transactionViewModel: TransactionViewModel = viewModel()

    val recipientName = remember { mutableStateOf(TextFieldValue()) }
    val amount = remember { mutableStateOf(TextFieldValue()) }
    val accountNumber = remember { mutableStateOf(TextFieldValue()) }
    val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFF5C6BC0), Color(0xFF1A237E))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundGradient)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Send Money",
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color.White
                ),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = recipientName.value,
                        onValueChange = { recipientName.value = it },
                        label = { Text("Recipient Name") },
                        placeholder = { Text("Enter recipient's name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFF1F3F6)
                        )
                    )

                    // New account number field
                    TextField(
                        value = accountNumber.value,
                        onValueChange = { accountNumber.value = it },
                        label = { Text("Account Number") },
                        placeholder = { Text("Enter recipient's account number") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFF1F3F6)
                        )
                    )

                    TextField(
                        value = amount.value,
                        onValueChange = { amount.value = it },
                        label = { Text("Amount") },
                        placeholder = { Text("Enter amount to send") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFF1F3F6)
                        )
                    )

                    Button(
                        onClick = {
                            val recipient = recipientName.value.text.trim()
                            val account = accountNumber.value.text.trim()
                            val amountText = amount.value.text.trim()

                            if (recipient.isEmpty() || account.isEmpty() || amountText.isEmpty()) {
                                Toast.makeText(
                                    navController.context,
                                    "Please fill all fields",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                sendMoney(navController, recipient, account, amountText, transactionViewModel)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C6BC0))
                    ) {
                        Text("Send Money", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFF5C6BC0),
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text("Back")
                    }
                }
            }
        }
    }
}

// Updated sendMoney function to handle the transaction
fun sendMoney(
    navController: NavController,
    recipient: String,
    account: String,
    amount: String,
    transactionViewModel: TransactionViewModel
) {
    val parsedAmount = amount.toDoubleOrNull()

    if (parsedAmount == null) {
        Toast.makeText(
            navController.context,
            "Please enter a valid numeric amount",
            Toast.LENGTH_SHORT
        ).show()
        return
    }

    // ✅ Generate the current date in yyyy-MM-dd format
    val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    val transaction = Transaction(
        title = "Sent to $recipient",
        amount = parsedAmount,
        date = currentDate,
        recipientName = recipient,
        accountNumber = account
    )

    transactionViewModel.insert(transaction)

    Toast.makeText(
        navController.context,
        "Sending Ksh $amount to $recipient",
        Toast.LENGTH_LONG
    ).show()

    navController.navigate("confirm/$recipient/$amount")
}