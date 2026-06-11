package edu.ucne.juan_guillen_ap2_p1.presentation.edit

data class EditAmonestacionUIState(
    val amonestacionId: Int? = null,
    val nombres: String = "",
    val razon: String = "",
    val monto: Double = 0.0,
    val nombresError: String? = null,
    val razonError: String? = null,
    val montoError: String? = null,
    val isSaving: Boolean = false,
    val isDeleting: Boolean = false,
    val isNew: Boolean = true,
    val saved: Boolean = false,
    val deleted: Boolean = false
)

