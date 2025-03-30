package com.example.mobileappassignment_tagntrac.views.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductLogoText(productLogo: String) {
    Text(
        text = productLogo,
        fontSize = 20.sp, // Equivalent to title3
        fontWeight = FontWeight.Bold,
        color = Color.Blue,
        modifier = Modifier
            .padding(16.dp)
            .background(Color.Blue.copy(alpha = 0.1f), shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun LogoText(logotext: String) {
    Box(
        modifier = Modifier
            .size(width = 80.dp, height = 40.dp)
            .background(Color.Blue.copy(alpha = 0.1f), shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = logotext,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )
    }
}

@Composable
fun NextButtonView(buttonText: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(12.dp)
        ) {
            Text(text = buttonText, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.ArrowForward, contentDescription = "Next")
        }
    }
}

// Feature Card Component
@Composable
fun FeatureCard(title: String, icon: ImageVector, color: Color) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(150.dp),
        shape = RoundedCornerShape(12.dp),
        color = color.copy(alpha = 0.1f),
        tonalElevation = 1.dp,
        border = BorderStroke(1.dp, color)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(icon, contentDescription = title, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(30.dp))
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = "Lorem Ipsum is simply dummy text", fontSize = 12.sp, color = Color.Gray)
        }
    }
}

// Logout Dialog
@Composable
fun LogoutDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirm Logout") },
        text = { Text("Are you sure you want to logout?") },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text("Logout", color = Color.Red) }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}