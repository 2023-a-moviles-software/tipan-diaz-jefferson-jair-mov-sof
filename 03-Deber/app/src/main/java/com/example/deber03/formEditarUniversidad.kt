package com.example.deber03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class formEditarUniversidad : AppCompatActivity() {
    val arregloCopiaUniversidad = EBaseDatos.tabla_UNI_FACU?.consultarTablaUniversidad()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_editar_universidad)
        //recopilo la informacion
        val positionSO = intent.getIntExtra("positionSO", -1)
        val universidad = arregloCopiaUniversidad?.get(positionSO)
        //asigno a las casillas el nombre de cada array
        val nombreUniversidad = findViewById<EditText>(R.id.pt_cambiar_nombreSO)
        val fechaFundacionUniversidad = findViewById<EditText>(R.id.pt_cambiar_versionSO)
        val numEstudiantesUniversidad = findViewById<EditText>(R.id.pt_cambiar_distribusionSO)
        if (universidad != null) {
            nombreUniversidad.setText(universidad.nombreUniversidad)
            fechaFundacionUniversidad.setText(universidad.fechaFundacion)
            numEstudiantesUniversidad.setText(universidad.numeroEstudiantes)
        }

        //Boton
        val botonGuardarCambios = findViewById<Button>(R.id.btn_guardarCambiosUni)
        botonGuardarCambios.setOnClickListener(){
            val nuevoNombreUni = nombreUniversidad.text.toString()
            val nuevaFechaFundacionUni = fechaFundacionUniversidad.text.toString()
            val nuevoNumEstudiantesUni = numEstudiantesUniversidad.text.toString()

            devolverRespuesta(nuevoNombreUni, nuevaFechaFundacionUni, nuevoNumEstudiantesUni, positionSO)
        }

    }
    fun devolverRespuesta(nombreUni: String, fechaFundacion: String, numEstudiantes: String, posicion: Int ){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nuevoNombreUni", nombreUni)
        intentDevolverParametros.putExtra("nuevaFechaFundacionUni", fechaFundacion)
        intentDevolverParametros.putExtra("nuevoNumEstudiantesUni", numEstudiantes)
        intentDevolverParametros.putExtra("positionSO", posicion)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )

        finish()
    }
}