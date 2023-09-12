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
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog

class listaFacultades : AppCompatActivity() {
    var idU = 0
    var datosUniversidad = EBaseDatos.tabla_UNI_FACU?.consultarTablaUniversidad()

    val callbackContenidoIntentExplicitoPrograma =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
                result ->
            if(result.resultCode == Activity.RESULT_OK){
                if(result.data != null){
                    //Lógica de negocio
                    val data = result.data
                    val nuevoNombreFacultad = "${data?.getStringExtra("nuevoNombreFacu")}"
                    val nuevaFechaFundacionFacultad = "${data?.getStringExtra("nuevaFechaFundacionFacu")}"
                    val nuevoNumEstudiantesFacultad = "${data?.getStringExtra("nuevoNumEstudiantesFacu")}"
                    // Crear la nueva facultad
                    EBaseDatos.tabla_UNI_FACU?.crearFacultad(idU,nuevoNombreFacultad, nuevaFechaFundacionFacultad, nuevoNumEstudiantesFacultad)
                    // Actualizar la lista de facultades
                    val listViewFacultades = findViewById<ListView>(R.id.lv_programas)
                    val adaptador = listViewFacultades.adapter as ArrayAdapter<BFacultad>
                    adaptador.clear()
                    val nuevalistaFacultades = EBaseDatos.tabla_UNI_FACU?.consultarFacultadesPorIdUniversidad(idU)
                    if (nuevalistaFacultades != null){
                        adaptador.addAll(nuevalistaFacultades)
                    }
                    adaptador.notifyDataSetChanged()
                }
            }
        }
    val callbackContenidoIntentExplicitoEditarFacultad =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    val nuevoNombreFacu = data?.getStringExtra("nuevoNombreFacu")
                    val nuevaFechaFundacionFacu = data?.getStringExtra("nuevaFechaFundacionFacu")
                    val nuevoNumEstudiantesFacu = data?.getStringExtra("nuevoNumEstudiantesFacu")
                    val idU = data?.getIntExtra("idU", -1)
                    val idFacultad = data?.getIntExtra("idFacultad", -1)

                    if (idFacultad != null && idU != null && nuevoNombreFacu != null && nuevaFechaFundacionFacu != null && nuevoNumEstudiantesFacu != null) {
                        EBaseDatos.tabla_UNI_FACU?.actualizarFacultadFormulario(idFacultad, idU, nuevoNombreFacu, nuevaFechaFundacionFacu, nuevoNumEstudiantesFacu)
                        //Actualizar base de datoss
                        val listViewFacultades = findViewById<ListView>(R.id.lv_programas)
                        val adaptador = listViewFacultades.adapter as ArrayAdapter<BFacultad>
                        adaptador.clear()
                        val nuevaListaFacultades = EBaseDatos.tabla_UNI_FACU?.consultarFacultadesPorIdUniversidad(idU)
                        if (nuevaListaFacultades != null) {
                            adaptador.addAll(nuevaListaFacultades) // Agregar la nueva lista al adaptador
                        }
                        adaptador.notifyDataSetChanged()
                    }

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_facultades)

        //recopilo la informacion pasada a traves del inted con parámetros
        var position = intent.getIntExtra("position", 0)
        val universidad = datosUniversidad?.get(position)
        idU = universidad?.id!!

        //ListView
        val tvNombreUniversidad = findViewById<TextView>(R.id.tv_nombreSO)
        tvNombreUniversidad.text = universidad?.nombreUniversidad
        val listViewFacultad = findViewById<ListView>(R.id.lv_programas)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            EBaseDatos.tabla_UNI_FACU?.consultarFacultadesPorIdUniversidad(idU) ?: emptyList()
        )
        listViewFacultad.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrearPrograma = findViewById<Button>(R.id.btn_crear_programa)
        botonCrearPrograma.setOnClickListener(){
            anadirFacultad(formCrearFacultad::class.java)
        }
        registerForContextMenu(listViewFacultad)
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_programa, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val positionUniversidad = info.position

        return when(item.itemId){
            R.id.mi_editar_programa -> {
                    editarFacultad(positionUniversidad, idU, formEditarFacultad::class.java )
                true
            }
            R.id.mi_eliminar_programa -> {

                abrirDialogo(positionUniversidad)
                true
            }
            else -> super.onContextItemSelected(item)
        }

    }
    fun abrirDialogo(positionPrograma: Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar la facultad?")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener { dialog, which ->
                eliminarFacultad(positionPrograma)
            }
        )
        builder.setNegativeButton("Cancelar", null)

        val dialogo = builder.create()
        dialogo.show()
    }
    fun eliminarFacultad(positionPrograma: Int) {

        val listViewFacultades = findViewById<ListView>(R.id.lv_programas)
        val adaptador = listViewFacultades.adapter as ArrayAdapter<BFacultad>
        //Obtener la lista actual de elementos y eliminar el elemento en la posición dada
        val listaFacultad = adaptador.getItem(positionPrograma)
        val idPR = listaFacultad?.idFacultad
        if (idPR != null) {
            EBaseDatos.tabla_UNI_FACU?.eliminarFacultadFormulario(idPR, idU)
        }
        adaptador.remove(listaFacultad)
        adaptador.notifyDataSetChanged()
    }
    fun anadirFacultad(clase: Class<*>){
        val intentExplicito = Intent(this, clase)
        callbackContenidoIntentExplicitoPrograma.launch(intentExplicito)
    }

    fun editarFacultad(posicionPR: Int, idU: Int, clase: Class<*>) {
        val intentExplicitoEditarFacultad = Intent(this, clase)
        intentExplicitoEditarFacultad.putExtra("positionPr", posicionPR)
        intentExplicitoEditarFacultad.putExtra("idU", idU)
        callbackContenidoIntentExplicitoEditarFacultad.launch(intentExplicitoEditarFacultad)
    }

}