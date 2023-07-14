package com.example.b1_examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class NuevoConjunto : AppCompatActivity() {
    var arreglo = BaseDatosMemoria.arregloConjunto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_conjunto)
        val botonCrearConjunto = findViewById<Button>(R.id.btn_crear_conjunto)
        botonCrearConjunto.setOnClickListener {
            var nombreConjunto = (findViewById<EditText>(R.id.txt_nombre_conjunto)).getText().toString()
            var direccion = (findViewById<EditText>(R.id.txt_direccion)).getText().toString()
            var RUC = (findViewById<EditText>(R.id.txt_ruc)).getText().toString()
            var telefono = Integer.parseInt((findViewById<EditText>(R.id.txt_telefono)).getText().toString())
            var propietario = (findViewById<EditText>(R.id.txt_propietario)).getText().toString()

            arreglo.add(Conjunto(nombreConjunto,direccion,RUC,telefono,propietario))

            finish()

        }
    }
}