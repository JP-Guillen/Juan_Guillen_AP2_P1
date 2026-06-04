package edu.ucne.juan_guillen_ap2_p1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.juan_guillen_ap2_p1.presentation.navigation.AppNavHost
import edu.ucne.juan_guillen_ap2_p1.ui.theme.Juan_Guillen_AP2_P1Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Juan_Guillen_AP2_P1Theme {
                AppNavHost(navController = rememberNavController())
            }
        }
    }
}