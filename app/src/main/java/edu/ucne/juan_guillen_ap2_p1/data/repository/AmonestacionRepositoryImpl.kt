package edu.ucne.juan_guillen_ap2_p1.data.repository

import edu.ucne.juan_guillen_ap2_p1.data.local.dao.AmonestacionDao
import edu.ucne.juan_guillen_ap2_p1.data.local.mapper.toDomain
import edu.ucne.juan_guillen_ap2_p1.data.local.mapper.toEntity
import edu.ucne.juan_guillen_ap2_p1.domain.model.Amonestacion
import edu.ucne.juan_guillen_ap2_p1.domain.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AmonestacionRepositoryImpl @Inject constructor(
    private val dao: AmonestacionDao
) : AmonestacionRepository {

    override fun observeAll(): Flow<List<Amonestacion>> =
        dao.observeAll().map { entities -> entities.map { it.toDomain() } }

    override suspend fun getById(id: Int): Amonestacion? =
        dao.getById(id)?.toDomain()

    override suspend fun upsert(amonestacion: Amonestacion): Int {
        val entity = amonestacion.toEntity()
        val result = dao.upsert(entity)
        return if (amonestacion.amonestacionId == 0) result.toInt() else amonestacion.amonestacionId
    }

    override suspend fun delete(id: Int) {
        dao.deleteById(id)
    }
}