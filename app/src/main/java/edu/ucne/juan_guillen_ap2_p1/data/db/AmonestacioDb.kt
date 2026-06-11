package edu.ucne.juan_guillen_ap2_p1.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.juan_guillen_ap2_p1.data.local.dao.AmonestacionDao
import edu.ucne.juan_guillen_ap2_p1.data.local.entity.AmonestacionEntity

@Database(
    entities = [
        AmonestacionEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AmonestacionDb : RoomDatabase() {
    abstract fun amonestacionDao(): AmonestacionDao
}