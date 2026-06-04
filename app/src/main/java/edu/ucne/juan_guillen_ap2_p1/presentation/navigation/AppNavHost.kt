package edu.ucne.juan_guillen_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.juan_guillen_ap2_p1.presentation.edit.EditBorrameScreen
import edu.ucne.juan_guillen_ap2_p1.presentation.list.ListBorrameScreen


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ListBorrameScreen(onNuevo = { navController.navigate("edit") })
        }
        composable("edit") {
            EditBorrameScreen(onVolver = { navController.popBackStack() })
        }
    }
}