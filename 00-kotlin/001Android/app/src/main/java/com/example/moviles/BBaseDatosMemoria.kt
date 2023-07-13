package com.example.moviles
// BbaseDatosMemoria.kt
class BBaseDatosMemoria {
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1, "Jefferson", "a@a.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2, "Jair", "b@b.com")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Carolina", "c@c.com")
                )
        }
    }
}