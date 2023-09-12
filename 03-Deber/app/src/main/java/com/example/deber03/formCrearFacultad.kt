package com.example.deber03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class  formCrearFacultad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_crear_facultad)
        val botonGuardar = findViewById<Button>(R.id.btn_crearFacultad)
        botonGuardar.setOnClickListener(){
            val nombrePr = findViewById<EditText>(R.id.tv_nombrePrograma).text.toString()
            val versionPr = findViewById<EditText>(R.id.tv_versionPrograma).text.toString()
            val almacenamientoPr = findViewById<EditText>(R.id.tv_almacenamientoPro).text.toString()
            devolverRespuesta(nombrePr, versionPr, almacenamientoPr)
        }
    }
    fun devolverRespuesta(nombreFacultad: String, fechaFundacion: String, numeroEstudiantes: String ){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nuevoNombreFacu", nombreFacultad)
        intentDevolverParametros.putExtra("nuevaFechaFundacionFacu", fechaFundacion)
        intentDevolverParametros.putExtra("nuevoNumEstudiantesFacu", numeroEstudiantes)

        setResult(
            RESULT_OK,
            intentDevolverParametros
        )

        finish()
    }
}