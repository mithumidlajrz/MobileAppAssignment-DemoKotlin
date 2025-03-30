package com.example.mobileappassignment_tagntrac.views.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileappassignment_tagntrac.model.FeatureCardVariant
import com.example.mobileappassignment_tagntrac.model.HomeTabs
import com.example.mobileappassignment_tagntrac.viewmodel.SignInViewModel
import com.example.mobileappassignment_tagntrac.views.components.FeatureCard
import com.example.mobileappassignment_tagntrac.views.components.LogoutDialog
import kotlinx.coroutines.delay

import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeBaseView(
    viewModel: SignInViewModel,
    onLogout: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(HomeTabs.Home) }
    var showLogoutDialog by remember { mutableStateOf(false) }
    var isLoggingOut by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(selectedTab.title, color = Color.White) },
                actions = {
                    if (selectedTab == HomeTabs.More) {
                        IconButton(onClick = { showLogoutDialog = true }) {
                            Icon(Icons.Filled.ExitToApp, contentDescription = "Logout", tint = Color.White)
                        }
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color(0xFF0A47AB))
            )
        },
        bottomBar = {
            BottomNavigationBar(selectedTab) { selectedTab = it }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (selectedTab) {
                HomeTabs.Home -> HomeView()
                HomeTabs.Assetization -> CustomColumn("Assetization")
                HomeTabs.Sites -> CustomColumn("Sites")
                HomeTabs.Shipments -> CustomColumn("Shipments")
                HomeTabs.More -> CustomColumn("More")
            }

            if (isLoggingOut) {
                LogoutOverlay()
            }
        }
    }

    if (showLogoutDialog) {
        LogoutDialog(
            onConfirm = {
                showLogoutDialog = false
                scope.launch {
                    isLoggingOut = true
                    delay(1500) // Simulating logout process with a delay
                    onLogout()
                    isLoggingOut = false
                }
            },
            onDismiss = { showLogoutDialog = false }
        )
    }
}

@Composable
fun CustomColumn(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) { Text(text) }
}

// Bottom Navigation
@Composable
fun BottomNavigationBar(selectedTab: HomeTabs, onTabSelected: (HomeTabs) -> Unit) {
    NavigationBar(containerColor = Color.White) {
        HomeTabs.entries.forEach { tab ->
            NavigationBarItem(
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                label = { Text(tab.title, fontSize = 11.sp) },
                icon = { Icon(tab.icon, contentDescription = tab.title) }
            )
        }
    }
}

// Logout Overlay
@Composable
fun LogoutOverlay() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.1f)),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(10.dp),
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text("Logging out...", fontSize = 14.sp)
            }
        }
    }
}

// Home Grid Layout
@Composable
fun HomeView() {
    val columns = GridCells.Fixed(2) // 2-column grid
    LazyVerticalGrid (
        columns = columns,
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(FeatureCardVariant.entries) { variant ->
            FeatureCard(variant.title, variant.icon, variant.color)
        }
    }
}


