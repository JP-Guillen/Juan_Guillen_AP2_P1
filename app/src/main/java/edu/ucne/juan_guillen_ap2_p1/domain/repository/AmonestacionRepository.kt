package edu.ucne.juan_guillen_ap2_p1.domain.repository

import edu.ucne.juan_guillen_ap2_p1.domain.model.Amonestacion
import kotlinx.coroutines.flow.Flow

interface AmonestacionRepository {
    fun observeAll(): Flow<List<Amonestacion>>
    suspend fun getById(id: Int): Amonestacion?
    suspend fun upsert(amonestacion: Amonestacion): Int
    suspend fun delete(id: Int)
}