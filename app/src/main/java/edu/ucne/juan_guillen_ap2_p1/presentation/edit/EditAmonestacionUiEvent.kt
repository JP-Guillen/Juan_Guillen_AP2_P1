package edu.ucne.juan_guillen_ap2_p1.presentation.edit

class EditAmonestacionUiEvent {
}sealed interface EditAmonestacionUIEvent {
    data class Load(val id: Int?) : EditAmonestacionUIEvent
    data class NombresChanged(val value: String) : EditAmonestacionUIEvent
    data class RazonChanged(val value: String) : EditAmonestacionUIEvent
    data class MontoChanged(val value: Double) : EditAmonestacionUIEvent
    data object Save : EditAmonestacionUIEvent
    data object Delete : EditAmonestacionUIEvent
}