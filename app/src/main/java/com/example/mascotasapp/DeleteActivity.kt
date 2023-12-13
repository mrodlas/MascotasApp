package com.example.mascotasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mascotasapp.database.MisMascotasApp
import com.example.mascotasapp.databinding.ActivityDeleteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DeleteActivity : ActivityWithMenus() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btonEliminar.setOnClickListener {
            var n_propietario = binding.escribePropietario.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                // listamascotas contiene el listado de libros de un autor
                var listamascotas =
                    MisMascotasApp.database.propietariosDAO().mascotasdeunpropietario(n_propietario)

                for (i in 0 until listamascotas.mascotas.size) {
                    MisMascotasApp.database.mascotasDAO().eliminar_mascota(listamascotas.mascotas[i])
                }

                var prop =
                    MisMascotasApp.database.propietariosDAO().obtenerpropietario(n_propietario)
                MisMascotasApp.database.propietariosDAO().eliminar_propietario(prop)
            }
        }
    }
}