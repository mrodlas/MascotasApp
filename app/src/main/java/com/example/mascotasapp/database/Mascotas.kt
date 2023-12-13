package com.example.mascotasapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Mascotas (
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var nombre:String="",
    var esPerro:Boolean=true,
    var raza:String="",
    var fecha_nacimiento:String="",
    var duenio:String=""
)