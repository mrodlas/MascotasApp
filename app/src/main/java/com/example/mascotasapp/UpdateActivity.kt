package com.example.mascotasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mascotasapp.database.MisMascotasApp
import com.example.mascotasapp.databinding.ActivityUpdateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UpdateActivity : ActivityWithMenus() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnActualizar.setOnClickListener {
            var n_propietario = binding.nPropietario.text.toString()
            var nuevaDireccion = binding.nuevaDireccion.text.toString()  // Obtén la nueva dirección desde el formulario

            CoroutineScope(Dispatchers.IO).launch {
                // Actualizar la dirección en la tabla de propietarios
                MisMascotasApp.database.propietariosDAO().actualizarDireccion(
                    n_propietario,
                    nuevaDireccion
                )

                // Opcional: Actualizar la dirección en las mascotas asociadas
                var listamascotas =
                    MisMascotasApp.database.propietariosDAO().mascotasdeunpropietario(n_propietario)

                for (i in 0 until listamascotas.mascotas.size) {
                    listamascotas.mascotas[i].duenio= nuevaDireccion
                    MisMascotasApp.database.mascotasDAO().actualizarMascota(listamascotas.mascotas[i])
                }
            }
        }
    }
}