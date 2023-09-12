package com.example.deber03

class BFacultad (
    var idU:Int,
    var idFacultad: Int,
    var nombreFacultad:String?,
    var fechaFundacion:String?,
    var numeroEstudiantes:String?,
) {
    override fun toString(): String {
        return "${nombreFacultad} - ${numeroEstudiantes} - ${fechaFundacion}"
    }
}