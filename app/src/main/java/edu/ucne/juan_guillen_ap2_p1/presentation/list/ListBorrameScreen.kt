package edu.ucne.juan_guillen_ap2_p1.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBorrameScreen(onNuevo: () -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Lista") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onNuevo) {
                Icon(Icons.Filled.Add, contentDescription = "Nuevo")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentAlignment = Alignment.Center
        ) {
        }
    }
}