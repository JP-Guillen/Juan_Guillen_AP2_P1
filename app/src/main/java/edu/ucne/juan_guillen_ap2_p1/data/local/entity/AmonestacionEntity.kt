package edu.ucne.juan_guillen_ap2_p1.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "amonestacion")
data class AmonestacionEntity(
    @PrimaryKey(autoGenerate = true)
    val amonestacionId: Int = 0,
    val nombres: String,
    val razon: String,
    val monto: Double = 0.0
)