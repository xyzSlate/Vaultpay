package com.nicholas.vaultpay.ui.screens.splash


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nicholas.vaultpay.R
import com.nicholas.vaultpay.navigation.ROUT_LOGIN
import com.nicholas.vaultpay.ui.theme.Purple80
import com.nicholas.vaultpay.ui.theme.VaultPayTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VaultPayTheme { // Using a theme to apply consistent styling
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }
}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavController) {


    val coroutine = rememberCoroutineScope()
    coroutine.launch {
        delay(3000)
        navController.navigate(ROUT_LOGIN)
    }
    // Using a Box to layer elements on top of each other.
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 1. Background Image
        Image(
            painter = painterResource(id = R.drawable.backgroundimage1), // Replace with your actual image resource.  Make sure you add a suitable image to your res/drawable folder
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop, // Ensure the image fills the entire screen
            // Use a ColorFilter to achieve the purple overlay.  This is approximate, you might need to fine-tune.
            //colorFilter = ColorFilter.tint(Color.Purple.copy(alpha = 0.6f), blendMode = BlendMode.Multiply) //Old way of setting color filter
        )

        // 2. Content Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Logo - using a placeholder, replace with your actual logo.
            Surface( // Using a Surface for the rounded background
                modifier = Modifier.size(100.dp),
                shape = RoundedCornerShape(16.dp), // Rounded corners for the logo background
                color = White, // Background color for the logo
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_vaultpay_logo), // Replace with your logo
                    contentDescription = "Bank Logo",
                    modifier = Modifier.padding(16.dp).size(68.dp), //Added padding and size
                    contentScale = ContentScale.Fit //Make the logo fit inside the surface
                )
            }
            Spacer(modifier = Modifier.height(32.dp))

            // VaultPay Text
            Text(
                text = "Secure. Smart. Seamless.",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif, // You can use a more specific font if you have one.
                    fontWeight = FontWeight.Bold,
                    fontSize = 23.sp,
                    color = Purple80,
                )
            )
            Spacer(modifier = Modifier.height(48.dp))

        }
        // Top Status Bar Content ( সিম and 13.45 ) -  Positioned at the top of the screen
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp) //Added padding
                .align(Alignment.TopCenter), // Align to the top center
            verticalAlignment = Alignment.CenterVertically, // Align items vertically in the row
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

        }
    }
}
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    VaultPayTheme {
        Surface {
            SplashScreen(navController= rememberNavController())
        }
    }
}
