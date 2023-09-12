package com.example.deber03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class FormCrearUniversidad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_crear_universidad)
        val botonGuardar = findViewById<Button>(R.id.btn_guardarUni)
        botonGuardar.setOnClickListener(){
            val nombreSO = findViewById<EditText>(R.id.pt_nombreSO).text.toString()
            val version = findViewById<EditText>(R.id.pt_versionSO).text.toString()
            val distribucion = findViewById<EditText>(R.id.pt_distribusionSO).text.toString()
            if (nombreSO != null && version != null && distribucion != null) {
                devolverRespuesta(nombreSO, version, distribucion)
            }
        }
    }
    fun devolverRespuesta(nombreUni: String, fechaFundacion: String, numeroEstudiantes: String ){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nuevoNombreUni", nombreUni)
        intentDevolverParametros.putExtra("nuevaFechaFundacionUni", fechaFundacion)
        intentDevolverParametros.putExtra("nuevoNumEstudiantesUni", numeroEstudiantes)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )

        finish()
    }

}