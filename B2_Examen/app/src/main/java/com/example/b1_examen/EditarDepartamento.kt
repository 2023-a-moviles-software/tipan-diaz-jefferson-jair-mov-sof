package com.example.b1_examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditarDepartamento : AppCompatActivity() {
    var arreglo = BaseDatosMemoria.arregloConjunto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_departamento)
        val objetoIntent : Intent = intent
        val idConjunto = objetoIntent.getIntExtra("idConjunto",10)
        val idDepartamento = objetoIntent.getIntExtra("idDepartamento",10)

        findViewById<TextView>(R.id.tv_nombre_departamento_editado).setText(arreglo[idConjunto].departamento[idDepartamento].nombreDepartamento)
        findViewById<EditText>(R.id.txt_nombre_editado).setText(arreglo[idConjunto].departamento[idDepartamento].nombreDepartamento)
        findViewById<EditText>(R.id.txt_precio_editado).setText((arreglo[idConjunto].departamento[idDepartamento].precio).toString())
        findViewById<EditText>(R.id.txt_cantidad_editado).setText((arreglo[idConjunto].departamento[idDepartamento].cantidad).toString())
        findViewById<EditText>(R.id.txt_torre_editada).setText(arreglo[idConjunto].departamento[idDepartamento].torreDepartamento)

        val botonActualizarDepartamento = findViewById<Button>(R.id.btn_actualizar_departamento)
        botonActualizarDepartamento.setOnClickListener {
            var nombreDepartamento = findViewById<EditText>(R.id.txt_nombre_editado).text.toString()
            var precioDepartamento = (findViewById<EditText>(R.id.txt_precio_editado).text.toString()).toDouble()
            var cantidadDepartamento = (findViewById<EditText>(R.id.txt_cantidad_editado).text.toString()).toInt()
            var familiaDepartamento = findViewById<EditText>(R.id.txt_torre_editada).text.toString()

            arreglo[idConjunto].departamento[idDepartamento].nombreDepartamento = nombreDepartamento
            arreglo[idConjunto].departamento[idDepartamento].precio = precioDepartamento
            arreglo[idConjunto].departamento[idDepartamento].cantidad = cantidadDepartamento
            arreglo[idConjunto].departamento[idDepartamento].torreDepartamento = familiaDepartamento

            finish()

        }

    }
}