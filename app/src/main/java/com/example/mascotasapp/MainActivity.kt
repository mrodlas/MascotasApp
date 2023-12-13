package com.example.mascotasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mascotasapp.database.Mascotas
import com.example.mascotasapp.database.MisMascotasApp
import com.example.mascotasapp.database.Propietarios
import com.example.mascotasapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ActivityWithMenus() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuración del boton guardar
        binding.btnGuardar.setOnClickListener {
            // Obtiene los valores de los campos de entrada del propietario.
            var n_propietario= binding.nombrePropietario.text.toString()
            var dir_propietario= binding.direccionPropietario.text.toString()
            var tlf_propietario= binding.telefonoPropietario.text.toString()
            // Obtiene los valores de los campos de entrada del propietario.
            var n_mascota= binding.nombreMascota.text.toString()
            var tipo_perro= binding.radioPerro.isChecked
            var raz_mascota= binding.razaMascota.text.toString()
            var fec_mascota= binding.fechaNacMascota.text.toString()

            // Utiliza Coroutines para realizar operaciones en un hilo de fondo (IO).
            CoroutineScope(Dispatchers.IO).launch {
                // Accede a la base de datos a través del DAO y agrega un nuevo propietario.
                MisMascotasApp.database.propietariosDAO().anadirPropietario(
                    Propietarios(
                        nombre_propietario = n_propietario,
                        direccion_propietario = dir_propietario,
                        tlf_propietario = tlf_propietario,
                    )
                )
                MisMascotasApp.database.mascotasDAO().anadirMascota(
                    Mascotas(
                        nombre = n_mascota,
                        esPerro = tipo_perro,
                        raza= raz_mascota,
                        fecha_nacimiento = fec_mascota
                    )
                )
            }
            // Ejecuta en el hilo principal para actualizar la interfaz de usuario si es necesario.
            runOnUiThread { true }

            binding.nombreMascota.text.clear()
            binding.radioPerro.isChecked= false
            binding.razaMascota.text.clear()
            binding.fechaNacMascota.text.clear()
        }

        binding.btnOtraMascota.setOnClickListener {
            // Obtiene los valores de los campos de entrada del propietario.
            var n_mascota= binding.nombreMascota.text.toString()
            var tipo_perro= binding.radioPerro.isChecked
            var raz_mascota= binding.razaMascota.text.toString()
            var fec_mascota= binding.fechaNacMascota.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                MisMascotasApp.database.mascotasDAO().anadirMascota(
                    Mascotas(
                        nombre = n_mascota,
                        esPerro = tipo_perro,
                        raza= raz_mascota,
                        fecha_nacimiento = fec_mascota
                    )
                )
            }
        }
    }
}