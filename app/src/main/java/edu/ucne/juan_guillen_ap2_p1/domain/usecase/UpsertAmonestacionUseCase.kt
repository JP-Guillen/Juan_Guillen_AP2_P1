package edu.ucne.juan_guillen_ap2_p1.domain.usecase

import edu.ucne.juan_guillen_ap2_p1.domain.model.Amonestacion
import edu.ucne.juan_guillen_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Inject

class UpsertAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository,
    private val validateAmonestacionUseCase: ValidateAmonestacionUseCase
) {
    suspend operator fun invoke(amonestacion: Amonestacion): Result<Int> {
        return try {
            val validation = validateAmonestacionUseCase(
                nombres = amonestacion.nombres,
                razon = amonestacion.razon,
                monto = amonestacion.monto
            )
            if (!validation.isValid) {
                val errorMsg = validation.nombresError
                    ?: validation.razonError
                    ?: validation.montoError
                    ?: "Error de validación"
                Result.failure(IllegalArgumentException(errorMsg))
            } else {
                val id = repository.upsert(amonestacion)
                Result.success(id)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
