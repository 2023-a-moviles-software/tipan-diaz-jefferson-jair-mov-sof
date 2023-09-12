package com.example.deber03

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelper(contexto: Context?,): SQLiteOpenHelper(
    contexto,
    "CRUD", // nombre bdd
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaUniversidad =
            """
                CREATE TABLE IF NOT EXISTS UNIVERSIDAD(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombreUniversidad VARCHAR(40),
                    fechaFundacion VARCHAR(10),
                    numeroEstudiantes VARCHAR(5)
                )
            """.trimIndent()
        val scriptSQLCrearTablaFacultad =
            """
        CREATE TABLE IF NOT EXISTS FACULTAD (
            idFacultad INTEGER PRIMARY KEY AUTOINCREMENT,
            idU INTEGER,
            nombreFacultad VARCHAR(40),
            fechaFundacion VARCHAR(10),
            numeroEstudiantes VARCHAR(5),
            FOREIGN KEY (idU) REFERENCES UNIVERSIDAD(id) ON DELETE CASCADE ON UPDATE CASCADE
        )
        """.trimIndent()
        db?.apply{
            execSQL(scriptSQLCrearTablaUniversidad)
            execSQL(scriptSQLCrearTablaFacultad)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?,
                           oldVersion: Int,
                           newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun crearUniversidad(nombreUniversidad: String, fechaFundacion: String, numeroEstudiantes: String): Boolean {
        val baseDatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombreUniversidad", nombreUniversidad)
        valoresAGuardar.put("fechaFundacion", fechaFundacion)
        valoresAGuardar.put("numeroEstudiantes", numeroEstudiantes)
        val resultadoGuardar = baseDatosEscritura
            .insert(
                "UNIVERSIDAD", // nombre tabla
                null,
                valoresAGuardar, // valores
            )
        baseDatosEscritura.close()
        return if (resultadoGuardar.toInt() === -1) false else true
    }
    fun crearFacultad(idU: Int, nombreFacultad: String, fechaFundacion: String, numeroEstudiantes: String): Boolean {
        val baseDatosEscrituraPR = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("idU", idU)
        valoresAGuardar.put("nombreFacultad", nombreFacultad)
        valoresAGuardar.put("fechaFundacion", fechaFundacion)
        valoresAGuardar.put("numeroEstudiantes", numeroEstudiantes)
        val resultadoGuardar = baseDatosEscrituraPR
            .insert(
                "FACULTAD", // nombre tabla
                null,
                valoresAGuardar, // valores
            )
        baseDatosEscrituraPR.close()
        return if (resultadoGuardar.toInt() === -1) false else true
    }

    fun consultarTablaUniversidad(): List<BUniversidad> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM UNIVERSIDAD
        """.trimIndent()

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            null // No se requieren parámetros en esta consulta
        )

        val listaUniversidad = mutableListOf<BUniversidad>()

        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val id = resultadoConsultaLectura.getInt(0)
                val nombreUniversidad = resultadoConsultaLectura.getString(1)
                val fechaFundacion = resultadoConsultaLectura.getString(2)
                val numeroEstudiantes = resultadoConsultaLectura.getString(3)
                val universidad = BUniversidad(id, nombreUniversidad, fechaFundacion, numeroEstudiantes)
                listaUniversidad.add(universidad)
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaUniversidad
    }

    fun eliminarUniFormulario(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        // where ID = ?
        val parametrosConsultaDelete = arrayOf(id.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "UNIVERSIDAD", // Nombre tabla
                "id=?", // Consulta Where
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun eliminarFacultadFormulario(id: Int, idU: Int): Boolean {
        val conexionEscritura = writableDatabase
        // where ID = ? AND idSO = ?
        val parametrosConsultaDelete = arrayOf(id.toString(), idU.toString())
        val resultadoEliminacion = conexionEscritura
            .delete(
                "FACULTAD", // Nombre tabla
                "idFacultad=? AND idU=?", // Consulta Where con dos condiciones
                parametrosConsultaDelete
            )
        conexionEscritura.close()
        return resultadoEliminacion != -1
    }

    fun consultarFacultadesPorIdUniversidad(idUniversidad: Int): List<BFacultad> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = """
            SELECT * FROM FACULTAD WHERE idU = ?
        """.trimIndent()

        val parametrosConsultaLectura = arrayOf(idUniversidad.toString())

        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura
        )

        val listaFacultades = mutableListOf<BFacultad>()

        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val idFacultad = resultadoConsultaLectura.getInt(0)
                val idUniversidad = resultadoConsultaLectura.getInt(1)
                val nombreFacultad = resultadoConsultaLectura.getString(2)
                val fechaFundacion = resultadoConsultaLectura.getString(3)
                val numeroEstudiantes = resultadoConsultaLectura.getString(4)

                val facultad = BFacultad(idUniversidad, idFacultad, nombreFacultad, fechaFundacion, numeroEstudiantes)
                listaFacultades.add(facultad)
            } while (resultadoConsultaLectura.moveToNext())
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaFacultades
    }


    fun actualizarUniFormulario(
        id: Int, nombreUniversidad: String, fechaFundacion: String, numeroEstudiantes: String
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombreUniversidad", nombreUniversidad)
        valoresAActualizar.put("fechaFundacion", fechaFundacion)
        valoresAActualizar.put("numeroEstudiantes", numeroEstudiantes)
        // where ID = ?
        val parametrosConsultaActualizar = arrayOf(id.toString())
        val resultadoActualizacion = conexionEscritura
            .update(
                "UNIVERSIDAD", // Nombre tabla
                valoresAActualizar, // Valores
                "id=?", // Consulta Where
                parametrosConsultaActualizar
            )
        conexionEscritura.close()
        return if (resultadoActualizacion.toInt() == -1) false else true
    }

    fun actualizarFacultadFormulario(idFacultad: Int, idU: Int, nombreFacultad: String, fechaFundacion: String, numeroEstudiantes: String): Boolean {
        val conexionEscrituraPR = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombreFacultad", nombreFacultad)
        valoresAActualizar.put("numeroEstudiantes", numeroEstudiantes)
        valoresAActualizar.put("fechaFundacion", fechaFundacion)
        // where ID = ? AND idSO = ?
        val parametrosConsultaActualizar = arrayOf(idFacultad.toString(), idU.toString())
        val resultadoActualizacion = conexionEscrituraPR
            .update(
                "FACULTAD", // Nombre tabla
                valoresAActualizar, // Valores
                "idFacultad=? AND idU=?", // Consulta Where
                parametrosConsultaActualizar
            )
        conexionEscrituraPR.close()
        return resultadoActualizacion != -1
    }

}