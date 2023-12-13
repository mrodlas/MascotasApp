package com.example.mascotasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mascotasapp.database.MisMascotasApp
import com.example.mascotasapp.databinding.ActivityMostrarBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MostrarActivity : ActivityWithMenus() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMostrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMostrar.setOnClickListener {
            var propietario_introducido = binding.nPropietario.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                var listamascotas = MisMascotasApp.database.propietariosDAO()
                    .mascotasdeunpropietario(propietario_introducido)

                // Filtrar perros y gatos
                val perros = listamascotas.mascotas.filter { it.esPerro}
                val gatos = listamascotas.mascotas.filter { !it.esPerro}

                // Obtener el número de perros y gatos
                val numeroPerros = perros.size
                val numeroGatos = gatos.size

                // Construir el mensaje a mostrar
                val mensajePerro = "Número de perros: $numeroPerros"
                val mensajeGato = "Número de perros: $numeroGatos"

                // Actualizar el TextView en el hilo principal
                runOnUiThread {
                    binding.numPerros.text = mensajePerro
                    binding.numGatos.text = mensajeGato
                }
            }
        }
    }
}