package com.example.elclarin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.elclarin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitTraer =  ClienteRetrofit.consumirApi.getTraer()
        retrofitTraer.enqueue(object : Callback<Noticias>{
            override fun onResponse(call: Call<Noticias>, response: Response<Noticias>) {
                binding.newSMostrar.text = response.body().toString()
            }

            override fun onFailure(call: Call<Noticias>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error al consultar API rest", Toast.LENGTH_SHORT).show()
            }

        })


    }
}