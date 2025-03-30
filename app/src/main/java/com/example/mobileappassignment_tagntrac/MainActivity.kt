package com.example.mobileappassignment_tagntrac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.mobileappassignment_tagntrac.navigation.AppNavigation
import com.example.mobileappassignment_tagntrac.ui.theme.MobileAppAssignmentTagNTracTheme
import com.example.mobileappassignment_tagntrac.viewmodel.SignInViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val homeViewModel = ViewModelProvider(this)[SignInViewModel::class]
        setContent {
            MobileAppAssignmentTagNTracTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(modifier = Modifier.padding(innerPadding), homeViewModel)
                }
            }
        }
    }
}

//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MobileAppAssignmentTagNTracTheme {
//        Greeting("Android")
//    }
//}