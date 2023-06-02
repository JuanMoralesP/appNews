package com.example.elclarin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.elclarin.databinding.ActivityMainBinding
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = findViewById<EditText>(R.id.userName)
        val btn : Button = findViewById<Button>(R.id.btnLogin)
        val checkBox1 = findViewById<CheckBox>(R.id.checkRecordar)

        val sharePreference = getSharedPreferences("MisDatosGuardados", Context.MODE_PRIVATE)
        val getUsername = sharePreference.getString("USERNAME", "")

        if(getUsername != ""){
            Toast.makeText(this, "avanza sin guardar: ${getUsername}", Toast.LENGTH_LONG).show()
            val i= Intent(this,Home::class.java)
            startActivity(i)
        }

        btn.setOnClickListener {
            if(user.text.toString() != ""){
                if(checkBox1.isChecked == true){
                    Toast.makeText(this, "Usuario Guardado", Toast.LENGTH_LONG).show()
                    val username = user.text.toString()
                    val editor = sharePreference.edit()
                    editor.putString("USERNAME",username)
                    editor.apply()
                    val i = Intent(this, Home::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "avanza sin guardar", Toast.LENGTH_LONG).show()
                    val enviarNomre = user.text.toString()
                    val enviar=Intent(this, Home::class.java)
                    enviar.putExtra("ENVIONAME", enviarNomre)
                    startActivity(enviar)

                }
            }else{
                Toast.makeText(this, "Ingresa un usuario para avanzar", Toast.LENGTH_LONG).show()
            }
        }


    }
}