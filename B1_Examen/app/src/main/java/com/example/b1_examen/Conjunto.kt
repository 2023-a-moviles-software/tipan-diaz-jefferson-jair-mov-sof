package com.example.b1_examen

class Conjunto (
    var nombreConjunto : String,
    var direccion : String,
    var ruc : String,
    var telefono : Int,
    var propietario: String,
    var departamento: ArrayList<Departamento> = arrayListOf <Departamento>(),
    var arrendamiento : ArrayList<Double>,
    var arrendamientoActual : Double

) {
    init {
        this.nombreConjunto; this.direccion; this.ruc; this.telefono; this.propietario
    }

    constructor(
        nombreConjunto: String,
        direccion: String,
        ruc: String,
        telefono: Int,
        propietario: String

    ) : this(
        nombreConjunto,
        direccion,
        ruc,
        telefono,
        propietario,
        departamento = ArrayList<Departamento>(),
        arrendamiento = ArrayList<Double>(),
        arrendamientoActual = 0.0
    )

    fun actualizarDepartamento(departamento : ArrayList<Departamento>){
        this.departamento = departamento
    }


    fun añadirDepartamento(departamento: Departamento) {
        this.departamento.add(departamento)
    }

    fun eliminarDepartamento(numeroDepartamento : Int): String {
        if (numeroDepartamento >= departamento.size || numeroDepartamento > 0) {
            val nombreDepartamentoEliminado = this.departamento.get(numeroDepartamento - 1).nombreDepartamento
            this.departamento.removeAt(numeroDepartamento - 1)
            return nombreDepartamentoEliminado
        } else {
            return "Departamento vendido o arrendado"
        }
    }

    fun añadirDepartamentoDisponible(numeroDepartamento: Int, cantidad: Int) : String{
        if (numeroDepartamento >= departamento.size) {
            if (cantidad > 0) {
                this.departamento.get(numeroDepartamento - 1).aumentarCantidad(cantidad)
                return "Se agrego $cantidad de ${departamento.get(numeroDepartamento - 1).nombreDepartamento} "
            } else {
                return "El valor de departamento no es permitido"
            }
        } else {
            return "Número de Departamentos no valido"
        }
    }
    fun comprarDepartamento(numeroDepartamento: Int, cantidad:Int) : String{
        if (numeroDepartamento >= departamento.size) {
            if (this.departamento.get(numeroDepartamento - 1).cantidad > 0 && cantidad
                <= this.departamento.get(numeroDepartamento - 1).cantidad) {
                var cantidadDisponible = this.departamento.get(numeroDepartamento - 1)
                    .disminuirCantidad(cantidad)
                this.arrendamientoActual = this.arrendamientoActual
                + this.departamento.get(numeroDepartamento - 1).precio * cantidad
                return arrendamientoActual.toString()
            } else {
                return "Departamento NO disponible"
            }
        } else {
            return "Numero de departamento no valido"
        }
    }

    fun finalizarContrato() : Double{
        this.arrendamiento.add(arrendamientoActual)
        var venta = arrendamientoActual
        this.arrendamientoActual = 0.0
        println("Contrato del departamento realizado con exito")
        return venta
    }

    override fun toString(): String {
        return "$nombreConjunto"
    }
}