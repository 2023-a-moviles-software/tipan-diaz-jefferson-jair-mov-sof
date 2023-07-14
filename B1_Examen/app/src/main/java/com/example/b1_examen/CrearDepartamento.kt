package com.example.b1_examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CrearDepartamento : AppCompatActivity() {
    var arreglo = BaseDatosMemoria.arregloConjunto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_departamento)
        var objetoIntent : Intent = intent
        val idConjunto = objetoIntent.getIntExtra("idConjuntoDepartamento",10)
        val botonCrearDepartamentoConjunto = findViewById<Button>(R.id.btn_crear_departamento)

        botonCrearDepartamentoConjunto.setOnClickListener {
            var nombreDepartamento = findViewById<EditText>(R.id.txt_nombre_departamento).text.toString()
            var precioDepartamento = (findViewById<EditText>(R.id.txt_precio).text.toString()).toDouble()
            var cantidadDepartamento = Integer.parseInt(findViewById<EditText>(R.id.txt_cantidad).text.toString())
            var familiaDepartamento = findViewById<EditText>(R.id.txt_ubicacion_torre).text.toString()

            arreglo[idConjunto].departamento.add(Departamento(
                nombreDepartamento,precioDepartamento,cantidadDepartamento,familiaDepartamento)
            )

            finish()
        }


    }
}