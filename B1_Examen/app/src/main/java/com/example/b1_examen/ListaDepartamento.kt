package com.example.b1_examen

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class ListaDepartamento : AppCompatActivity() {
    var arreglo = BaseDatosMemoria.arregloConjunto
    var adaptador : ArrayAdapter<Departamento>? = null
    var idConjunto : Int = 10
    var arregloDepartamento : ArrayList<Departamento>? = null
    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_departamento)
        val objetoIntent : Intent = intent
        idConjunto = objetoIntent.getIntExtra("idConjuntoListaDepartamento",3)
        val listView = findViewById<ListView>(R.id.lv_departamento)
        arregloDepartamento = arreglo.get(idConjunto).departamento
        adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1, //layout.xml que se va a usar
            arregloDepartamento!!
        )
        listView.adapter = adaptador
        registerForContextMenu(listView)
        findViewById<TextView>(R.id.tv_nombre_conjunto_departamento).setText(arreglo.get(idConjunto).nombreConjunto)
        var botonCrearDepartamento = findViewById<Button>(R.id.btn_crear_depart)
        botonCrearDepartamento.setOnClickListener {
            intent = Intent(this, CrearDepartamento::class.java )
            intent.putExtra("idConjuntoDepartamento", idConjunto)
            startActivity(intent)
            adaptador!!.notifyDataSetChanged()
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_departamento, menu)
        //obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar_departamento -> {
                intent = Intent(this,EditarDepartamento::class.java)
                intent.putExtra("idConjunto",idConjunto)
                intent.putExtra("idDepartamento",idItemSeleccionado)
                startActivity(intent)
                return true
            }

            R.id.mi_eliminar_departamento -> {
                abrirDialogo()
                "Hacer algo con ${idItemSeleccionado}"
                return false
            }

            else -> super.onContextItemSelected(item)

        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { //Callback
                    dialog, which -> eliminarFruta()
                adaptador!!.notifyDataSetChanged()
            }
        )
        builder.setNegativeButton("Cancelar", null)
        val dialogo= builder.create()
        dialogo.show()
    }


    fun eliminarFruta(){
        arregloDepartamento?.removeAt(idItemSeleccionado)
    }



    override fun onResume() {
        super.onResume()
        adaptador!!.notifyDataSetChanged()
    }


}