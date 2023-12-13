package com.example.mascotasapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "propietario")
data class Propietarios (
    @PrimaryKey
    var nombre_propietario:String="",
    var direccion_propietario:String="",
    var tlf_propietario:String=""
)