package edu.ucne.juan_guillen_ap2_p1.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.juan_guillen_ap2_p1.domain.model.Amonestacion
import edu.ucne.juan_guillen_ap2_p1.domain.usecase.DeleteAmonestacionUseCase
import edu.ucne.juan_guillen_ap2_p1.domain.usecase.GetAmonestacionUseCase
import edu.ucne.juan_guillen_ap2_p1.domain.usecase.UpsertAmonestacionUseCase
import edu.ucne.juan_guillen_ap2_p1.domain.usecase.ValidateAmonestacionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAmonestacionViewModel @Inject constructor(
    private val getAmonestacionUseCase: GetAmonestacionUseCase,
    private val upsertAmonestacionUseCase: UpsertAmonestacionUseCase,
    private val deleteAmonestacionUseCase: DeleteAmonestacionUseCase,
    private val validateAmonestacionUseCase: ValidateAmonestacionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EditAmonestacionUIState())
    val state: StateFlow<EditAmonestacionUIState> = _state.asStateFlow()

    fun onEvent(event: EditAmonestacionUIEvent) {
        when (event) {
            is EditAmonestacionUIEvent.Load -> onLoad(event.id)
            is EditAmonestacionUIEvent.NombresChanged -> {
                _state.update { it.copy(nombres = event.value, nombresError = null) }
            }
            is EditAmonestacionUIEvent.RazonChanged -> {
                _state.update { it.copy(razon = event.value, razonError = null) }
            }
            is EditAmonestacionUIEvent.MontoChanged -> {
                _state.update { it.copy(monto = event.value, montoError = null) }
            }
            EditAmonestacionUIEvent.Save -> onSave()
            EditAmonestacionUIEvent.Delete -> onDelete()
        }
    }

    private fun onLoad(id: Int?) {
        if (id == null || id == 0) {
            _state.update { it.copy(isNew = true, amonestacionId = null) }
            return
        }
        viewModelScope.launch {
            val amonestacion = getAmonestacionUseCase(id)
            amonestacion?.let { item ->
                _state.update {
                    it.copy(
                        isNew = false,
                        amonestacionId = item.amonestacionId,
                        nombres = item.nombres,
                        razon = item.razon,
                        monto = item.monto
                    )
                }
            }
        }
    }

    private fun onSave() {
        viewModelScope.launch {
            val validation = validateAmonestacionUseCase(
                nombres = _state.value.nombres,
                razon = _state.value.razon,
                monto = _state.value.monto
            )
            if (!validation.isValid) {
                _state.update {
                    it.copy(
                        nombresError = validation.nombresError,
                        razonError = validation.razonError,
                        montoError = validation.montoError
                    )
                }
                return@launch
            }
            _state.update { it.copy(isSaving = true) }
            try {
                val amonestacion = Amonestacion(
                    amonestacionId = _state.value.amonestacionId ?: 0,
                    nombres = _state.value.nombres,
                    razon = _state.value.razon,
                    monto = _state.value.monto
                )
                val result = upsertAmonestacionUseCase(amonestacion)
                result.onSuccess { generatedId ->
                    _state.update { it.copy(isSaving = false, saved = true, amonestacionId = generatedId) }
                }.onFailure { exception ->
                    _state.update { it.copy(isSaving = false, nombresError = exception.message) }
                }
            } catch (e: Exception) {
                _state.update { it.copy(isSaving = false, nombresError = e.message) }
            }
        }
    }

    private fun onDelete() {
        val id = _state.value.amonestacionId ?: return
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            try {
                deleteAmonestacionUseCase(id)
                _state.update { it.copy(isDeleting = false, deleted = true) }
            } catch (e: Exception) {
                _state.update { it.copy(isDeleting = false, nombresError = e.message) }
            }
        }
    }
}