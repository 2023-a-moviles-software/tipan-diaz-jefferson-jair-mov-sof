package com.example.b1_examen

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    var arreglo = BaseDatosMemoria.arregloConjunto
    var idItemSeleccionado = 0
    var adaptador : ArrayAdapter<Conjunto>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // adaptador (Iterables)
        val listView = findViewById<ListView>(R.id.lv_conjunto)
        adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, //layout.xml que se va a usar
            arreglo
        )
        listView.adapter = adaptador
        registerForContextMenu(listView)
        adaptador!!.notifyDataSetChanged()
        val botonNuevoConjunto = findViewById<Button>(R.id.btn_crear)
        botonNuevoConjunto.setOnClickListener {
            irActividad(NuevoConjunto::class.java)
            adaptador!!.notifyDataSetChanged()
        }

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                intent = Intent(this,EditarConjunto::class.java)
                intent.putExtra("idConjunto",idItemSeleccionado)
                startActivity(intent)
                return true
            }

            R.id.mi_eliminar -> {
                abrirDialogo()
                "Hacer algo con ${idItemSeleccionado}"
                return false
            }

            R.id.mi_ver_Departamento -> {
                intent = Intent(this,ListaDepartamento::class.java)
                intent.putExtra("idConjuntoListaDepartamento",idItemSeleccionado)
                startActivity(intent)
                return false
            }

            else -> super.onContextItemSelected(item)

        }
    }

    override fun onResume() {
        super.onResume()
        adaptador!!.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        //NO RECIBIMOS RESPUESTA
        startActivity(intent)
        //this.startActivity()
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { //Callback
                    dialog, which -> eliminarConjunto()
                adaptador!!.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val dialogo= builder.create()
        dialogo.show()
    }

    fun eliminarConjunto(){
        arreglo.removeAt(idItemSeleccionado)
    }

}