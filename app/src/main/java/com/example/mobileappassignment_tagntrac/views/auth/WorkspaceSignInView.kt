package com.example.mobileappassignment_tagntrac.views.auth

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.DocumentScanner
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import com.example.mobileappassignment_tagntrac.viewmodel.SignInViewModel
import com.example.mobileappassignment_tagntrac.views.components.LogoText
import com.example.mobileappassignment_tagntrac.views.components.NextButtonView
import com.example.mobileappassignment_tagntrac.views.components.ProductLogoText

@Composable
fun WorkspaceSignInView(
    viewModel: SignInViewModel,
    onNavigateToHome: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    val isLoggingIn by viewModel.isLoggingIn.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Product Logo
            ProductLogoText("Product Logo")

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Logo", fontSize = 16.sp, color = Color.Blue)

            Spacer(modifier = Modifier.height(30.dp))

            // Email Input
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Password Input
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = "Toggle Password Visibility"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(5.dp))

            // Forgot Password & Error Message
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box {
                    errorMessage?.let {
                        Text(text = it, color = Color.Red, fontSize = 12.sp)
                    }
                }
                Text(
                    text = "Forgot Password?",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.clickable { /* Forgot Password Action */ }
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Sign-in Button
            NextButtonView("Sign In") {
                scope.launch {
                    signInButtonTapped(
                        email = email,
                        password = password,
                        viewModel = viewModel,
                        onSuccess = { onNavigateToHome() },
                        onError = { errorMessage = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(70.dp))

            // Powered By
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Powered By", fontSize = 12.sp, color = Color.Gray)
                LogoText("Logo")
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Scanner & Tracker Buttons
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                OutlinedButton(onClick = { /* Scanner Action */ }) {
                    Icon(Icons.Outlined.DocumentScanner, contentDescription = "Scanner")
                    Text(text = "Scanner")
                }
                OutlinedButton(onClick = { /* Tracker Action */ }) {
                    Icon(Icons.Filled.PinDrop, contentDescription = "Tracker")
                    Text(text = "Tracker")
                }
            }
        }
        if (isLoggingIn) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

// Function to Handle Sign-in
private suspend fun signInButtonTapped(
    email: String,
    password: String,
    viewModel: SignInViewModel,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    if (email.isEmpty() || password.isEmpty()) {
        onError("Email and password are required.")
        return
    }

    viewModel.login(email, password) { isSuccess, errorMsg ->
        if (isSuccess) {
            onSuccess()
        } else {
            onError(errorMsg ?: "Invalid credentials.")
        }
    }
}
