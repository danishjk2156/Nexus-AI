package com.nexusai.studio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nexusai.studio.navigation.NexusNavHost
import com.nexusai.studio.ui.theme.NexusAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NexusAITheme {
                val navController = rememberNavController()
                NexusNavHost(navController = navController)
            }
        }
    }
}
