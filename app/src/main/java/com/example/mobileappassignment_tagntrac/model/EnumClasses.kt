package com.example.mobileappassignment_tagntrac.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material.icons.outlined.Help
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SettingsRemote
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

// Enum classes
enum class HomeTabs(
    val title: String,
    val icon: ImageVector
) {
    Home("Home", Icons.Filled.Home),
    Assetization("Assetization", Icons.Outlined.Widgets),
    Sites("Sites", Icons.Outlined.Business),
    Shipments("Shipments", Icons.Default.LocalShipping),
    More("More", Icons.Filled.MoreHoriz);
}

enum class FeatureCardVariant(
    val title: String,
    val icon: ImageVector,
    val color: Color
) {
    Assetization("Assetization", Icons.Outlined.Widgets, Color.Blue),
    Sites("Sites", Icons.Outlined.Business, Color(0xFFFFA500)), // Orange
    Shipments("Shipments", Icons.Outlined.LocalShipping, Color.Yellow),
    Scan("Scan", Icons.Outlined.QrCodeScanner, Color.Green),
    AssetSearch("Asset Search", Icons.Outlined.Search, Color.Magenta),
    DeviceSearch("Device Search", Icons.Outlined.SettingsRemote, Color(0xFF008080)), // Teal
    Settings("Settings", Icons.Outlined.Settings, Color.Yellow),
    Help("Help", Icons.Outlined.Help, Color(0xFF4B0082)); // Indigo
}









