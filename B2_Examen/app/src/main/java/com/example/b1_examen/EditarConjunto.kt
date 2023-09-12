package com.example.b1_examen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditarConjunto : AppCompatActivity() {
    var arreglo = BaseDatosMemoria.arregloConjunto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_conjunto)
        val objetoIntent : Intent = intent
        var idConjunto = objetoIntent.getIntExtra("idConjunto",10)
        findViewById<TextView>(R.id.tv_titulo_conjunto_editado).setText(arreglo.get(idConjunto).nombreConjunto)
        findViewById<EditText>(R.id.txt_nombre_conjunto_edit).setText(arreglo.get(idConjunto).nombreConjunto)
        findViewById<EditText>(R.id.txt_direccion_conjunto_edit).setText(arreglo.get(idConjunto).direccion)
        findViewById<EditText>(R.id.txt_RUC_edit).setText(arreglo.get(idConjunto).ruc)
        findViewById<EditText>(R.id.txt_telefono_editado).setText((arreglo.get(idConjunto).telefono.toString()))
        findViewById<EditText>(R.id.txt_propietario_editado).setText(arreglo.get(idConjunto).propietario)

        val botonActualizarConjunto = findViewById<Button>(R.id.btn_Actualizar_conjunto)
        botonActualizarConjunto.setOnClickListener {
            var nombreConjuntoAct = findViewById<EditText>(R.id.txt_nombre_conjunto_edit).text.toString()
            var direccionAct = findViewById<EditText>(R.id.txt_direccion_conjunto_edit).text.toString()
            var RUCAct = findViewById<EditText>(R.id.txt_RUC_edit).text.toString()
            var telefonoAct = Integer.parseInt(findViewById<EditText>(R.id.txt_telefono_editado).text.toString())
            var propietarioAct = findViewById<EditText>(R.id.txt_propietario_editado).text.toString()
            arreglo.get(idConjunto).nombreConjunto = nombreConjuntoAct
            arreglo.get(idConjunto).direccion = direccionAct
            arreglo.get(idConjunto).ruc = RUCAct
            arreglo.get(idConjunto).telefono = telefonoAct
            arreglo.get(idConjunto).propietario = propietarioAct

            finish()

        }
    }
}