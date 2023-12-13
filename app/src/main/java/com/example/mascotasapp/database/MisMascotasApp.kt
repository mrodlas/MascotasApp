package com.example.mascotasapp.database

import android.app.Application
import androidx.room.Room

class MisMascotasApp:Application() {
    companion object {
        lateinit var database: DBMascotas
    }

    override fun onCreate() {
        super.onCreate()
        MisMascotasApp.database = Room.databaseBuilder(this, DBMascotas::class.java,"DBMascotas").build()
    }
}