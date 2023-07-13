package com.example.moviles

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import java.nio.file.attribute.AclEntry.Builder

class BListView : AppCompatActivity() {
    val arreglo = BBaseDatosMemoria.arregloBEntrenador
    var idItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        // Adaptador
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // layout.xml que se va usar
        arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)   //AQUIII
        botonAnadirListView.setOnClickListener {
            anadirEntrenador(adaptador)
        }
        registerForContextMenu(listView)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId){
            R.id.mi_editar ->{
                "Hacer algo con: ${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminar ->{
                abrirDialogo()
                "Hacer algo con: ${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }

    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eleminar")
        builder.setPositiveButton(
            "Aceptar", DialogInterface.OnClickListener{ //Callback
                dialog, which ->  // ALGUNA COSA
            }
        )
        builder.setNegativeButton("Cancelar", null)

        val opciones = resources.getStringArray(
            R.array.string_array_opciones_dialogo
        )
        val seleccionPrevia = booleanArrayOf(
            true, // Lunes seleccionado
            false, // Martes NO seleccionado
            false, // Miercoles No seleccionado
        )

        builder.setMultiChoiceItems(
            opciones,
            seleccionPrevia,
            {
                dialog, which, isChecked ->
                "Dio clic en el item ${which}"
            }
        )
        val dialogo = builder.create()
        dialogo.show()
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenar las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        // Obtener el id del ArrayList seleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    fun anadirEntrenador(
        adaptador: ArrayAdapter<BEntrenador>
    ){
        arreglo.add(
            BEntrenador(4, "Jefferson", "Descripcion")
        )
        adaptador.notifyDataSetChanged()
    }
}