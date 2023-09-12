package com.example.deber03

class listaFacultad {
    companion object{
        var arregloFacultades = arrayListOf<BFacultad>()
        init {
            arregloFacultades.add(BFacultad(1,1,"F.de Sistemas","27/08/1869","5000"))
            arregloFacultades.add(BFacultad(1,2,"F.de Petroleos","27/08/1869","4000"))
            arregloFacultades.add(BFacultad(2,1,"F.de Medicina","05/11/1620","1.3"))
            arregloFacultades.add(BFacultad(2,2,"F.de Ciencias Sociales","05/11/1620","1.1"))
        }
    }
}