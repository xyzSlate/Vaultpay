package com.nicholas.vaultpay.ui.screens.support

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.nicholas.vaultpay.R

@Composable
fun SupportScreen() {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.backgroundimagelate),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay Gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF5C6BC0).copy(alpha = 0.7f), Color.Black.copy(alpha = 0.4f))
                    )
                )
        )

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Support Center",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Need help? Contact us at:\n\n📧 support@vaultpay.com\n📞 0792451363",
                color = Color.White.copy(alpha = 0.9f),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp),
            )

            Spacer(modifier = Modifier.height(32.dp))

            // SMS Button
            Button(
                onClick = {
                    val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = "smsto:0792451363".toUri()
                        putExtra("sms_body", "Hello, I need help with VaultPay.")
                    }
                    context.startActivity(smsIntent)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE1BEE7)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Icon(Icons.Default.MailOutline, contentDescription = null, tint = Color.Black)
                Spacer(Modifier.width(8.dp))
                Text("Send SMS", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            // Call Button
            Button(
                onClick = {
                    val callIntent = Intent(Intent.ACTION_DIAL).apply {
                        data = "tel:0792451363".toUri()
                    }
                    context.startActivity(callIntent)
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE1BEE7)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Icon(Icons.Default.Call, contentDescription = null, tint = Color.Black)
                Spacer(Modifier.width(8.dp))
                Text("Call Us", color = Color.Black, fontWeight = FontWeight.Bold)
            }

            // Email Button
            Button(
                onClick = {
                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = "mailto:support@vaultpay.com".toUri()
                        putExtra(Intent.EXTRA_SUBJECT, "VaultPay Support Request")
                        putExtra(Intent.EXTRA_TEXT, "Hello VaultPay Support,\n\nI need help with...")
                    }
                    context.startActivity(Intent.createChooser(emailIntent, "Send Email"))
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE1BEE7)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Icon(Icons.Default.Email, contentDescription = null, tint = Color.Black)
                Spacer(Modifier.width(8.dp))
                Text("Email Support", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}
