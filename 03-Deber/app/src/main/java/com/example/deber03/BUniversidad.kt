package com.example.deber03

class BUniversidad (
    var id:Int,
    var nombreUniversidad:String?,
    var fechaFundacion:String?,
    var numeroEstudiantes:String?,
) {
    override fun toString(): String {
        return "${id} - ${nombreUniversidad} - ${fechaFundacion} - ${numeroEstudiantes}"
    }
}