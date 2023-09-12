package com.example.deber03

class listaUniversidad {
    companion object{
        var arregloUniversidad = arrayListOf<BUniversidad>()
        init {
            arregloUniversidad.add(BUniversidad(1, "EPN","27/08/1869", "30889"))
            arregloUniversidad.add(BUniversidad(2, "UCE","05/11/1620", "50000"))
        }
    }
}