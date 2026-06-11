package edu.ucne.juan_guillen_ap2_p1.presentation.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EditAmonestacionScreen(
    amonestacionId: Int?,
    onNavigateBack: () -> Unit,
    viewModel: EditAmonestacionViewModel = hiltViewModel()
) {
    LaunchedEffect(amonestacionId) {
        viewModel.onEvent(EditAmonestacionUIEvent.Load(amonestacionId))
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.saved, state.deleted) {
        if (state.saved || state.deleted) onNavigateBack()
    }

    EditAmonestacionBody(
        state = state,
        onEvent = viewModel::onEvent,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditAmonestacionBody(
    state: EditAmonestacionUIState,
    onEvent: (EditAmonestacionUIEvent) -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(if (state.isNew) "Nueva Amonestación" else "Editar Amonestación") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = state.nombres,
                onValueChange = { onEvent(EditAmonestacionUIEvent.NombresChanged(it)) },
                label = { Text("Nombres") },
                isError = state.nombresError != null,
                supportingText = { state.nombresError?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = state.razon,
                onValueChange = { onEvent(EditAmonestacionUIEvent.RazonChanged(it)) },
                label = { Text("Razón") },
                isError = state.razonError != null,
                supportingText = { state.razonError?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = if (state.monto == 0.0) "" else state.monto.toString(),
                onValueChange = { texto ->
                    val valor = texto.toDoubleOrNull() ?: 0.0
                    onEvent(EditAmonestacionUIEvent.MontoChanged(valor))
                },
                label = { Text("Monto") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = state.montoError != null,
                supportingText = { state.montoError?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(24.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { onEvent(EditAmonestacionUIEvent.Save) },
                    enabled = !state.isSaving,
                    modifier = Modifier.weight(1f)
                ) {
                    if (state.isSaving) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text("Guardar")
                    }
                }

                if (!state.isNew) {
                    Spacer(Modifier.width(8.dp))
                    OutlinedButton(
                        onClick = { onEvent(EditAmonestacionUIEvent.Delete) },
                        enabled = !state.isDeleting,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditAmonestacionNewPreview() {
    MaterialTheme {
        EditAmonestacionBody(
            state = EditAmonestacionUIState(isNew = true),
            onEvent = {},
            onNavigateBack = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EditAmonestacionEditPreview() {
    MaterialTheme {
        EditAmonestacionBody(
            state = EditAmonestacionUIState(
                isNew = false,
                amonestacionId = 1,
                nombres = "Juan Pérez",
                razon = "Llegada tardía",
                monto = 1500.0
            ),
            onEvent = {},
            onNavigateBack = {}
        )
    }
}