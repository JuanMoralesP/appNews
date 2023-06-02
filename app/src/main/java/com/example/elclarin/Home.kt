package com.example.elclarin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.example.elclarin.databinding.ActivityMainBinding
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_home)

        val DISPLAY = findViewById<TextView>(R.id.textView2)
        val infoApi = findViewById<TextView>(R.id.newSMostrar)
        val sharePreference = getSharedPreferences("MisDatosGuardados", Context.MODE_PRIVATE)
        val username=sharePreference.getString("USERNAME","").toString()
        val bundle = intent.extras
        val dato = bundle?.getString("ENVIONAME")
        if(username != ""){
            DISPLAY.text="El usuario es: ${username}"
        }else{
            DISPLAY.text= "Esto se escribió: "+dato
        }

        //consumo API


        val retrofitTraer =  ClienteRetrofit.consumirApi.getTraer()
        retrofitTraer.enqueue(object : Callback<Noticias> {
            override fun onResponse(call: Call<Noticias>, response: Response<Noticias>) {
                infoApi.text = response.body().toString()
            }

            override fun onFailure(call: Call<Noticias>, t: Throwable) {
                Toast.makeText(this@Home, "Error al consultar API rest", Toast.LENGTH_SHORT).show()
            }

        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //funcion que permite la visualizacion del layout
        menuInflater.inflate(R.menu.menu_navegar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //funcion que permite realizar acciones con el item seleccionado
        when(item.itemId){
            R.id.buscar -> Toast.makeText(this, "Borrado", Toast.LENGTH_LONG).show()
            R.id.salir -> salir()
        }

        return super.onOptionsItemSelected(item)
    }

    fun salir(){
        val sharePreference = getSharedPreferences("MisDatosGuardados", Context.MODE_PRIVATE)
        val editor = sharePreference.edit()
        editor.putString("USERNAME","")
        editor.apply()
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}