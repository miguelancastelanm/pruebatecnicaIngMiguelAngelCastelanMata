package com.example.pruebatecnicamacm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.pruebatecnicamacm.data.RetrofitServiceFactory
import com.example.pruebatecnicamacm.data.model.Result

import com.example.pruebatecnicamacm.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
/*

        BY INGENIERO MIGUEL ANGEL CASTELAN MATA
        25/04/2025
*
* */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val service = RetrofitServiceFactory.makeRetrofitService()

        lifecycleScope.launch {
            val detallesServices = service.listDetalle()
            println(detallesServices)
            binding.recycler.visibility = View.VISIBLE
            binding.recycler.adapter = DataAdapter(
                detallesServices.results,
                object :DataClickedListener{
                    override fun onDataClicked(data: Result) {
                        lifecycleScope.launch {
                            val detallesServices1 = service.listDetalle1(data.id.toString()+"")
                            Toast.makeText(this@MainActivity,detallesServices1.name,Toast.LENGTH_SHORT).show()
                            Glide.with(this@MainActivity).load(detallesServices1.image).into(binding.imageDetalle)
                            binding.general.visibility = View.VISIBLE
                            binding.nombre.text = detallesServices1.name
                            binding.estatus.text = detallesServices1.status

                            binding.especie.text = detallesServices1.species
                            binding.genero.text = detallesServices1.gender
                            binding.origen.text = detallesServices1.origin.name
                            binding.ubicacion.text = detallesServices1.location.name



                            binding.back.setOnClickListener { binding.general.visibility = View.GONE  }
                        }

                    }

                }
            )
        }



    }
}

