package edu.ucne.juan_guillen_ap2_p1.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.juan_guillen_ap2_p1.domain.usecase.ObserveAmonestacionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAmonestacionViewModel @Inject constructor(
    private val observeAmonestacionUseCase: ObserveAmonestacionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListAmonestacionUiState(isLoading = true))
    val state: StateFlow<ListAmonestacionUiState> = _state.asStateFlow()

    init {
        onEvent(ListAmonestacionUiEvent.Load)
    }

    fun onEvent(event: ListAmonestacionUiEvent) {
        when (event) {
            ListAmonestacionUiEvent.Load -> observeAmonestaciones()
            is ListAmonestacionUiEvent.ShowMessage -> _state.update { it.copy(message = event.message) }
            else -> {}
        }
    }

    private fun observeAmonestaciones() {
        viewModelScope.launch {
            observeAmonestacionUseCase().collectLatest { list ->
                _state.update { it.copy(isLoading = false, amonestaciones = list) }
            }
        }
    }
}