package edu.ucne.juan_guillen_ap2_p1.presentation.list

sealed interface ListAmonestacionUiEvent {
    data object Load : ListAmonestacionUiEvent
    data class Edit(val id: Int) : ListAmonestacionUiEvent
    data object CreateNew : ListAmonestacionUiEvent
    data class ShowMessage(val message: String) : ListAmonestacionUiEvent
}