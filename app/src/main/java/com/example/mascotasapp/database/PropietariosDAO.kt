package com.example.mascotasapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface PropietariosDAO {
    // Método para insertar un propietario en la tabla.
    @Insert
    fun anadirPropietario(propietarios: Propietarios)

    // Método para realizar una transacción y obtener todas las mascotas asociadas a un propietario específico.
    @Transaction
    @Query("SELECT * FROM propietario WHERE nombre_propietario like :propietarios")
    fun mascotasdeunpropietario(propietarios: String): PropietariosConMascotas

    // Método para obtener un propietario específico de la tabla.
    @Query("SELECT * FROM propietario WHERE nombre_propietario like :propietarios")
    fun obtenerpropietario(propietarios: String) : Propietarios

    // Método para eliminar un propietario de la tabla.
    @Delete
    fun eliminar_propietario(propietarios: Propietarios)

    @Update
    // Nuevo método para actualizar la dirección de un propietario
    @Query("UPDATE propietario SET direccion_propietario = :nuevaDireccion WHERE nombre_propietario LIKE :propietario")
    fun actualizarDireccion(propietario: String, nuevaDireccion: String)
}