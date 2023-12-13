package com.example.mascotasapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface MascotasDAO {
    @Insert
    fun anadirMascota(mascotas: Mascotas)

    @Delete
    fun eliminar_mascota(mascotas: Mascotas)

    // Nuevo método para actualizar una mascota
    @Update
    fun actualizarMascota(mascota: Mascotas)
}