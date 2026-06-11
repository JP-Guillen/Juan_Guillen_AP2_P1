package edu.ucne.juan_guillen_ap2_p1.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.juan_guillen_ap2_p1.domain.model.Amonestacion

@Composable
fun ListAmonestacionScreen(
    goToAmonestacion: (Int) -> Unit,
    createAmonestacion: () -> Unit,
    viewModel: ListAmonestacionViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ListAmonestacionBody(
        state = state,
        onEvent = { event ->
            when (event) {
                is ListAmonestacionUiEvent.Edit -> goToAmonestacion(event.id)
                is ListAmonestacionUiEvent.CreateNew -> createAmonestacion()
                else -> viewModel.onEvent(event)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListAmonestacionBody(
    state: ListAmonestacionUiState,
    onEvent: (ListAmonestacionUiEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Amonestaciones") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(ListAmonestacionUiEvent.CreateNew) }) {
                Icon(Icons.Default.Add, contentDescription = "Nueva Amonestación")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {
            if (state.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 8.dp)
                ) {
                    items(state.amonestaciones) { amonestacion ->
                        AmonestacionCard(
                            amonestacion = amonestacion,
                            onClick = { onEvent(ListAmonestacionUiEvent.Edit(amonestacion.amonestacionId)) }
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 80.dp, top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Amonestaciones: ${state.amonestaciones.size}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Total: $${"%,.2f".format(state.amonestaciones.sumOf { it.monto })}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun AmonestacionCard(
    amonestacion: Amonestacion,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(amonestacion.nombres, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text("Razón: ${amonestacion.razon}", style = MaterialTheme.typography.bodyMedium)
            Text("Monto: $${"%,.2f".format(amonestacion.monto)}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListAmonestacionBodyPreview() {
    MaterialTheme {
        ListAmonestacionBody(
            state = ListAmonestacionUiState(
                isLoading = false,
                amonestaciones = listOf(
                    Amonestacion(
                        amonestacionId = 1,
                        nombres = "Juan Pérez",
                        razon = "Llegada tardía",
                        monto = 1500.0
                    ),
                    Amonestacion(
                        amonestacionId = 2,
                        nombres = "María Gómez",
                        razon = "Ausencia injustificada",
                        monto = 3000.0
                    )
                )
            ),
            onEvent = {}
        )
    }
}