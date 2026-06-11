package edu.ucne.juan_guillen_ap2_p1.data.local.mapper

import edu.ucne.juan_guillen_ap2_p1.data.local.entity.AmonestacionEntity
import edu.ucne.juan_guillen_ap2_p1.domain.model.Amonestacion

fun AmonestacionEntity.toDomain(): Amonestacion =
    Amonestacion(
        amonestacionId = amonestacionId,
        nombres = nombres,
        razon = razon,
        monto = monto
    )

fun Amonestacion.toEntity(): AmonestacionEntity =
    AmonestacionEntity(
        amonestacionId = amonestacionId,
        nombres = nombres,
        razon = razon,
        monto = monto
    )