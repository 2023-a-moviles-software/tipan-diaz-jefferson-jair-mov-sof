package com.example.deber03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class formEditarFacultad : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_editar_facultad)
        //recopilo la informacion
        val posicionPR = intent.getIntExtra("positionPr", -1)
        val idSO = intent.getIntExtra("idU", -1)
        var datosPr = EBaseDatos.tabla_UNI_FACU?.consultarFacultadesPorIdUniversidad(idSO)
        val facultad = datosPr?.get(posicionPR)
        val idFACU = facultad?.idFacultad

        //asigno a las casillas el nombre de cada array
        val nombreFacultad = findViewById<EditText>(R.id.tv_nombreMod)
        nombreFacultad.setText(facultad?.nombreFacultad)
        val fechaFundacionFacultad = findViewById<EditText>(R.id.tv_versionMod)
        fechaFundacionFacultad.setText(facultad?.fechaFundacion)
        val numEstudiantesFacultad = findViewById<EditText>(R.id.tv_almacenamientoMod)
        numEstudiantesFacultad.setText(facultad?.numeroEstudiantes)

        //Boton
        val botonGuardarCambios = findViewById<Button>(R.id.btn_editar_facultad)
        botonGuardarCambios.setOnClickListener(){
            val nuevoNombreFacu = nombreFacultad.text.toString()
            val nuevaFechaFundacionFacu = fechaFundacionFacultad.text.toString()
            val nuevoNumEstudiantesFacu = numEstudiantesFacultad.text.toString()
            if (idFACU != null) {
                devolverRespuesta(nuevoNombreFacu, nuevaFechaFundacionFacu, nuevoNumEstudiantesFacu, idSO, idFACU)
            }
        }
    }
    fun devolverRespuesta(nombreFacu: String, fechaFundacionFacu: String, numEstudiantesFacu: String, idU: Int, idFacultad: Int ){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nuevoNombreFacu", nombreFacu)
        intentDevolverParametros.putExtra("nuevaFechaFundacionFacu", fechaFundacionFacu)
        intentDevolverParametros.putExtra("nuevoNumEstudiantesFacu", numEstudiantesFacu)
        intentDevolverParametros.putExtra("idU", idU)
        intentDevolverParametros.putExtra("idFacultad", idFacultad)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )

        finish()
    }
}