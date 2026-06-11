package edu.ucne.juan_guillen_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.juan_guillen_ap2_p1.data.local.entity.AmonestacionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AmonestacionDao {
    @Query("SELECT * FROM amonestacion ORDER BY amonestacionId DESC")
    fun observeAll(): Flow<List<AmonestacionEntity>>

    @Query("SELECT * FROM amonestacion WHERE amonestacionId = :id")
    suspend fun getById(id: Int): AmonestacionEntity?

    @Upsert
    suspend fun upsert(entity: AmonestacionEntity): Long

    @Delete
    suspend fun delete(entity: AmonestacionEntity)

    @Query("DELETE FROM amonestacion WHERE amonestacionId = :id")
    suspend fun deleteById(id: Int)
}