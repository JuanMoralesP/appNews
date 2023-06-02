package com.example.elclarin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val DISPLAY = findViewById<TextView>(R.id.textView2)
        val sharePreference = getSharedPreferences("MisDatosGuardados", Context.MODE_PRIVATE)
        val username=sharePreference.getString("USERNAME","").toString()
        val bundle = intent.extras
        val dato = bundle?.getString("ENVIONAME")
        if(username != ""){
            DISPLAY.text="El usuario es: ${username}"
        }else{
            DISPLAY.text= "Esto se escribió: "+dato
        }


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