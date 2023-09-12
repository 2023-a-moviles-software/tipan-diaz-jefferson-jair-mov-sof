package com.example.deber03

import android.app.Activity
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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    val callbackContenidoIntentExplicito =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    // Lógica de negocio
                    val data = result.data
                    val nuevoNombreUni = "${data?.getStringExtra("nuevoNombreUni")}"
                    val nuevaFechaFundacionUni = "${data?.getStringExtra("nuevaFechaFundacionUni")}"
                    val nuevoNumEstudiantesUni = "${data?.getStringExtra("nuevoNumEstudiantesUni")}"

                    // Crear el sistema operativo
                    EBaseDatos.tabla_UNI_FACU?.crearUniversidad(nuevoNombreUni, nuevaFechaFundacionUni, nuevoNumEstudiantesUni)

                    // Actualizar la lista de sistemas operativos en el adaptador
                    val listView = findViewById<ListView>(R.id.lv_list_view)
                    val adaptador = listView.adapter as ArrayAdapter<BUniversidad>
                    adaptador.clear() // Limpiar la lista actual en el adaptador
                    val nuevaListaUniversidades = EBaseDatos.tabla_UNI_FACU?.consultarTablaUniversidad()
                    if (nuevaListaUniversidades != null) {
                        adaptador.addAll(nuevaListaUniversidades) // Agregar la nueva lista al adaptador
                    }

                    // Notificar al adaptador que los datos han cambiado
                    adaptador.notifyDataSetChanged()
                }
            }
        }
    val callbackContenidoIntentExplicitoEditar =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    val nuevoNombreUni = data?.getStringExtra("nuevoNombreUni")
                    val nuevaFechaFundacionUni = data?.getStringExtra("nuevaFechaFundacionUni")
                    val nuevoNumEstudiantesUni = data?.getStringExtra("nuevoNumEstudiantesUni")
                    val positionSO = data?.getIntExtra("positionSO", -1)

                    // obtener informacion del elemento
                    val listView = findViewById<ListView>(R.id.lv_list_view)
                    val adaptador = listView.adapter as ArrayAdapter<BUniversidad>
                    val listaUniversidad = adaptador.getItem(positionSO!!)
                    val idU = listaUniversidad?.id
                    if (positionSO != null && positionSO != -1) {
                        //Actualizar Base de datos
                        if (idU != null && nuevoNombreUni != null && nuevaFechaFundacionUni != null && nuevoNumEstudiantesUni != null) {
                            EBaseDatos.tabla_UNI_FACU?.actualizarUniFormulario(idU, nuevoNombreUni, nuevaFechaFundacionUni, nuevoNumEstudiantesUni)
                        }
                        // Actualizar la lista de sistemas operativos en el adaptador
                        val listView = findViewById<ListView>(R.id.lv_list_view)
                        val adaptador = listView.adapter as ArrayAdapter<BUniversidad>
                        adaptador.clear() // Limpiar la lista actual en el adaptador
                        val nuevaListaUniversidades = EBaseDatos.tabla_UNI_FACU?.consultarTablaUniversidad()
                        if (nuevaListaUniversidades != null) {
                            adaptador.addAll(nuevaListaUniversidades) // Agregar la nueva lista al adaptador
                        }
                        // Notificar al adaptador que los datos han cambiado
                        adaptador.notifyDataSetChanged()
                    }
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EBaseDatos.tabla_UNI_FACU = ESqliteHelper(this)

        //Adaptador personalizado
        val listView = findViewById<ListView>(R.id.lv_list_view)
        var datosUniversidad = EBaseDatos.tabla_UNI_FACU?.consultarTablaUniversidad()
        if (datosUniversidad == null || datosUniversidad.isEmpty()) {
            for (universidad in listaUniversidad.arregloUniversidad) {
                val nombreUniversidad = universidad.nombreUniversidad
                val fechaFundacion = universidad.fechaFundacion
                val numeroEstudiantes = universidad.numeroEstudiantes

                if (nombreUniversidad != null && fechaFundacion != null && numeroEstudiantes != null) {
                    EBaseDatos.tabla_UNI_FACU?.crearUniversidad(nombreUniversidad, fechaFundacion, numeroEstudiantes)
                }
            }
            for (facultad in listaFacultad.arregloFacultades) {
                val idU = facultad.idU
                val nombreFacultad = facultad.nombreFacultad
                val fechaFundacion = facultad.fechaFundacion
                val numeroEstudiantes = facultad.numeroEstudiantes

                if (nombreFacultad != null && fechaFundacion != null && numeroEstudiantes != null) {
                    EBaseDatos.tabla_UNI_FACU?.crearFacultad(idU, nombreFacultad, fechaFundacion, numeroEstudiantes)
                }
            }
        }


        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            EBaseDatos.tabla_UNI_FACU?.consultarTablaUniversidad() ?: emptyList()
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAniadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
        botonAniadirListView.setOnClickListener(){
            crearUniversidad(FormCrearUniversidad::class.java)
        }
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?){
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val positionItemSeleccionado = info.position
        return when(item.itemId){
            R.id.mi_editar ->{
                editarUniversidad(positionItemSeleccionado, formEditarUniversidad::class.java)
                //Hacer algo con idSeleccionado
                return true
            }
            R.id.mi_eliminar ->{
                abrirDialogo(positionItemSeleccionado)
                //Hacer algo con idSeleccionado
                return true
            }
            R.id.mi_ver_programas -> {
                mostrarFacultad(positionItemSeleccionado)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(position: Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea elminar?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener(){
                    dialog, which ->  eliminarElemento(position)
            }
        )
        builder.setNegativeButton("Cancelar", null)

        val dialogo = builder.create()
        dialogo.show()
    }

    fun eliminarElemento(position: Int) {

        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = listView.adapter as ArrayAdapter<BUniversidad>

        // Obtener la lista actual de elementos y eliminar el elemento en la posición dada
        val listaUniversidades = adaptador.getItem(position)
        val idSO = listaUniversidades?.id
        if (idSO != null) {
            EBaseDatos.tabla_UNI_FACU?.eliminarUniFormulario(idSO)
        }
        adaptador.remove(listaUniversidades)

        // Notificar al adaptador que los datos han cambiado
        adaptador.notifyDataSetChanged()
    }

    fun crearUniversidad(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        //RECIBIMOS RESPUESTA
        callbackContenidoIntentExplicito.launch(intentExplicito)
    }

    private fun editarUniversidad(position: Int, clase: Class<*>) {
        val intentExplicitoEditarSO = Intent(this, clase)
        intentExplicitoEditarSO.putExtra("positionSO", position)
        callbackContenidoIntentExplicitoEditar.launch(intentExplicitoEditarSO)
    }
    fun mostrarFacultad(position: Int) {
        val intent = Intent(this, listaFacultades::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }
}