package com.nicholas.vaultpay.ui.screens.auth

import AuthViewModel
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nicholas.vaultpay.R
import com.nicholas.vaultpay.navigation.ROUT_ABOUT
import com.nicholas.vaultpay.navigation.ROUT_HOME
import com.nicholas.vaultpay.navigation.ROUT_REGISTER
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import com.nicholas.vaultpay.ui.theme.Purple80


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Observe login logic
    LaunchedEffect(authViewModel) {
        authViewModel.loggedInUser = { user ->
            if (user == null) {
                Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            } else {
                if (user.role == "user") {
                    navController.navigate(ROUT_HOME)
                } else {
                    navController.navigate(ROUT_ABOUT)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(colors = listOf(Color(0xFF00C6FF), Color(0xFF0072FF))))
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Animated Welcome Text
            AnimatedVisibility(
                visible = true,
                enter = fadeIn(animationSpec = tween(1000)),
                exit = fadeOut(animationSpec = tween(1000))
            ) {
                Text(
                    text = "Welcome Back!",
                    fontSize = 36.sp,
                    fontFamily = FontFamily.Serif,
                    color = Purple80,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }

            // Email Input
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color.Black) },
                leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon", tint = Color.Gray) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .background(Color.White, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF0072FF),
                    unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                    focusedTextColor = Color.Black,

                )
            )

            // Password Input with Show/Hide Toggle
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.Black) },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon", tint = Color.Gray) },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .background(Color.White, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    val image = if (passwordVisible) painterResource(R.drawable.visibilityoff1) else painterResource(R.drawable.visibility1)
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password", modifier = Modifier.size(20.dp))
                    }
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF0072FF),
                    unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),


                )
            )

            // Gradient Login Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF00C6FF), Color(0xFF0072FF))
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {
                        if (email.isBlank() || password.isBlank()) {
                            Toast.makeText(context, "Please enter email and password", Toast.LENGTH_SHORT).show()
                        } else {
                            authViewModel.loginUser(email, password)
                        }
                    },
                    modifier = Modifier.fillMaxSize(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Login", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Register Navigation Button
            TextButton(onClick = { navController.navigate(ROUT_REGISTER) }) {
                Text("Don't have an account? Register", color = Color.White)
            }
        }
    }
}
