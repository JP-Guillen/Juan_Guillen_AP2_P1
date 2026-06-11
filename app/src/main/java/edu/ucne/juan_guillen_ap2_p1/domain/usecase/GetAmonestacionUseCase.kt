package edu.ucne.juan_guillen_ap2_p1.domain.usecase

import edu.ucne.juan_guillen_ap2_p1.domain.model.Amonestacion
import edu.ucne.juan_guillen_ap2_p1.domain.repository.AmonestacionRepository
import javax.inject.Inject

class GetAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    suspend operator fun invoke(id: Int): Amonestacion? {
        if (id <= 0) throw IllegalArgumentException("El ID debe ser mayor que 0")
        return repository.getById(id)
    }
}
