package com.example.b1_examen

class BaseDatosMemoria {
    companion object {
        val arregloConjunto = arrayListOf<Conjunto>()
        init {
            val conjunto01 = Conjunto("Conjunto La Arcadia","Quito","1726597823001",95497312,
                "Darwin Velastegui")
            conjunto01.añadirDepartamento(Departamento(
                "Departamento 001",
                250.5,4,"Torre J"))
            arregloConjunto.add(conjunto01)
            arregloConjunto.add(Conjunto(
                "Conjunto Caupicho","Quito","1754893042001",
                947239510,"Vicente Cunguan"))
            arregloConjunto.add(Conjunto(
                "Conjunto Catamayo","Loja","1724658933001",
                932145698,"Viviana Palta"))
        }
    }
}