package edu.ucne.juan_guillen_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.juan_guillen_ap2_p1.presentation.edit.EditAmonestacionScreen
import edu.ucne.juan_guillen_ap2_p1.presentation.list.ListAmonestacionScreen

@Composable
fun AmonestacionNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.AmonestacionList
    ) {
        composable<Screen.AmonestacionList> {
            ListAmonestacionScreen(
                goToAmonestacion = { id -> navHostController.navigate(Screen.Amonestacion(id)) },
                createAmonestacion = { navHostController.navigate(Screen.Amonestacion(0)) }
            )
        }

        composable<Screen.Amonestacion> {
            val args = it.toRoute<Screen.Amonestacion>()
            EditAmonestacionScreen(
                amonestacionId = args.amonestacionId,
                onNavigateBack = { navHostController.navigateUp() }
            )
        }
    }
}