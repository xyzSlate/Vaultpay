package com.nicholas.vaultpay.ui.screens.auth

import AuthViewModel
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nicholas.vaultpay.R
import com.nicholas.vaultpay.model.User
import com.nicholas.vaultpay.navigation.ROUT_HOME
import com.nicholas.vaultpay.navigation.ROUT_LOGIN

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    authViewModel: AuthViewModel,
    navController: NavController,
    onRegisterSuccess: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var role by remember { mutableStateOf("user") }
    val context = LocalContext.current
    val animatedAlpha by animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1500, easing = LinearEasing)
    )
    val roleOptions = listOf("user", "admin")
    var expanded by remember { mutableStateOf(false) }

    // Gradient Background
    val backgroundBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFF6A11CB), Color(0xFF2575FC))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundBrush) // Gradient background
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Animated Title
        AnimatedVisibility(visible = true, enter = fadeIn(), exit = fadeOut()) {
            Text(
                text = "Create Your Account",
                fontSize = 36.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Username Input with Shadow and Rounded Corners
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = "Username Icon") },
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color(0xFF2575FC),
                unfocusedBorderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email Input with Shadow and Rounded Corners
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Filled.Email, contentDescription = "Email Icon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color(0xFF2575FC),
                unfocusedBorderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Role Dropdown with Shadow and Rounded Corners
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = role,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select Role") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.Transparent,
                    focusedBorderColor = Color(0xFF2575FC),
                    unfocusedBorderColor = Color.Black
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                roleOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            role = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password Input with Show/Hide Toggle
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Password Icon") },
            trailingIcon = {
                val image = if (passwordVisible) painterResource(R.drawable.visibility1) else painterResource(R.drawable.visibilityoff1)
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(image, contentDescription = if (passwordVisible) "Hide Password" else "Show Password",modifier = Modifier.size(20.dp))
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color(0xFF2575FC),
                unfocusedBorderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password Input with Show/Hide Toggle
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = "Confirm Password Icon") },
            trailingIcon = {
                val image = if (confirmPasswordVisible) painterResource(R.drawable.visibility1) else painterResource(R.drawable.visibilityoff1)
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(image, contentDescription = if (confirmPasswordVisible) "Hide Password" else "Show Password",modifier = Modifier.size(20.dp))
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color(0xFF2575FC),
                unfocusedBorderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Register Button with Gradient and Shadow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF00C6FF), Color(0xFF0072FF))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .shadow(8.dp, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {
                    if (username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
                        Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show()
                    } else if (password != confirmPassword) {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    } else {
                        authViewModel.registerUser(User(username = username, email = email, role = role, password = password))
                        onRegisterSuccess()
                    }
                },
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Text("Register", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login Link
        TextButton(onClick = { navController.navigate(ROUT_LOGIN) }) {
            Text("Already have an account? Login", color = Color.White)
        }
    }
}
