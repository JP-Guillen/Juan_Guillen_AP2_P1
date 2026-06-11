package edu.ucne.juan_guillen_ap2_p1.domain.usecase

import edu.ucne.juan_guillen_ap2_p1.domain.model.Amonestacion
import edu.ucne.juan_guillen_ap2_p1.domain.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    operator fun invoke(): Flow<List<Amonestacion>> = repository.observeAll()
}
