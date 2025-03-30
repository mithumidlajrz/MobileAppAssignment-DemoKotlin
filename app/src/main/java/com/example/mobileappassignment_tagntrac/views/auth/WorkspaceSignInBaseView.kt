package com.example.mobileappassignment_tagntrac.views.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import com.example.mobileappassignment_tagntrac.viewmodel.SignInViewModel
import com.example.mobileappassignment_tagntrac.views.components.LogoText
import com.example.mobileappassignment_tagntrac.views.components.NextButtonView
import com.example.mobileappassignment_tagntrac.views.components.ProductLogoText

@Composable
fun WorkspaceSignInBaseView(
    viewModel: SignInViewModel,
    onNavigateToWorkspaceSignIn: () -> Unit
) {
    var workspaceID by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Product Logo
        ProductLogoText("Product Logo")

        Spacer(modifier = Modifier.height(10.dp))

        // Title Section
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Sign in to your workspace",
                fontSize = 30.sp
            )
            Text(
                text = "Enter your workspace identifier",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }

        // Workspace Identifier Input
        Column {
            OutlinedTextField(
                value = workspaceID,
                onValueChange = { workspaceID = it },
                label = { Text("Workspace Identifier") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .padding(12.dp)
            )

            // error message if any
            errorMessage?.let {
                Text(
                    text = it,
                    fontSize = 12.sp,
                    color = Color.Red,
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                )
            }
        }

        // Next Button
        NextButtonView(buttonText = "Next") {
            /* Handle Next button click */
            scope.launch {
                idLoginTapped(
                    workspaceID.text,
                    viewModel,
                    onSuccess = { onNavigateToWorkspaceSignIn() },
                    onError = { errorMessage = it }
                )
            }
        }

        // Powered By Section
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Powered By",
                fontSize = 12.sp,
                color = Color.Gray
            )
            LogoText("Logo")
        }
    }
}

// Function for login validation
private suspend fun idLoginTapped(
    workspaceID: String,
    viewModel: SignInViewModel,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    if (workspaceID.isEmpty()) {
        onError("Workspace ID is required")
        return
    }

    viewModel.workspaceIdLogin(workspaceID) { isSuccess, errorMsg ->
        if (isSuccess) {
            onSuccess()
        } else {
            onError(errorMsg ?: "Invalid workspace ID")
        }
    }
}