package com.example.mascotasapp.database

import androidx.room.Embedded
import androidx.room.Relation

// Definición de una clase de datos llamada PropietariosConMascotas.
data class PropietariosConMascotas (
    // Anotación @Embedded: Indica que se debe incluir la clase Propietarios como parte de esta clase.
    @Embedded val propietario: Propietarios,
    // Anotación @Relation: Establece una relación entre las tablas Propietarios y Mascotas.
    @Relation(
        // Indica la columna de la tabla Propietarios que se relaciona con la columna de la tabla Mascotas.
        parentColumn = "nombre_propietario",
        // Indica la columna de la tabla Mascotas que se relaciona con la columna de la tabla Propietarios.
        entityColumn = "duenio"
    )
    // Lista de mascotas pertenecientes al propietario. Puede haber múltiples mascotas para un propietario.
    val mascotas: MutableList<Mascotas>
)