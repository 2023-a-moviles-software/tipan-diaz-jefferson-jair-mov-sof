package com.example.b1_examen

class Departamento (var nombreDepartamento: String,
                    var precio: Double,
                    var cantidad: Int,
                    var disponibilidad: Boolean,
                    var torreDepartamento: String
) {
    init {
        this.nombreDepartamento; this.precio; this.cantidad; this.torreDepartamento; this.disponibilidad
    }

    constructor(
        nombre: String,
        precio: Double,
        cantidad : Int,
        torreDepartamento : String,
    ) : this(
        nombre, precio, cantidad, disponibilidad = true, torreDepartamento
    ){
        if (cantidad > 0){
            this.disponibilidad = true
        } else {
            this.disponibilidad = false
        }
    }

    fun disminuirCantidad(cantidad : Int){
        this.cantidad = this.cantidad - cantidad
    }

    fun aumentarCantidad(cantidad : Int){
        this.cantidad = this.cantidad + cantidad
    }

    override fun toString(): String {
        return "Departamento: $nombreDepartamento  Precio: $precio  Cantidad: $cantidad"
    }

}