package edu.ucne.juan_guillen_ap2_p1.domain.usecase

import edu.ucne.juan_guillen_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Inject

class ValidateAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    data class ValidationResult(
        val isValid: Boolean = false,
        val nombresError: String? = null,
        val razonError: String? = null,
        val montoError: String? = null
    )

    suspend operator fun invoke(
        nombres: String,
        razon: String,
        monto: Double
    ): ValidationResult {

        val nombresError = when {
            nombres.isBlank() -> "El nombre no puede estar vacío."
            else -> null
        }

        val razonError = when {
            razon.isBlank() -> "La razón no puede estar vacía."
            else -> null
        }

        val montoError = when {
            monto <= 0.0 -> "El monto debe ser mayor que 0."
            else -> null
        }

        return ValidationResult(
            isValid = nombresError == null && razonError == null && montoError == null,
            nombresError = nombresError,
            razonError = razonError,
            montoError = montoError
        )
    }
}