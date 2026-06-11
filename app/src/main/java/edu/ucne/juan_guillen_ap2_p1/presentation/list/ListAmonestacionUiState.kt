package edu.ucne.juan_guillen_ap2_p1.presentation.list

import edu.ucne.juan_guillen_ap2_p1.domain.model.Amonestacion

data class ListAmonestacionUiState(
    val isLoading: Boolean = false,
    val amonestaciones: List<Amonestacion> = emptyList(),
    val message: String? = null
)