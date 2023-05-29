import java.util.Date

fun main(args: Array<String>) {
    println("Hello World!")

    // INMUTABLES (NO se reasignan "=")
    val inmutable: String = "Jefferson";
    //inmutable = "Jair";

    //Mutables (Re asignar)
    var mutable: String = "Jefferson"
    mutable= "Jair"

    //val > var

    //Duck Typing
    var ejemploVariable = " Jefferson Tipan "
    val edadEjemplo: Int 12
    ejemploVariable().trim()
    //ejemploVariable

    //variable primitivo
    val nombreProfesor : String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadCivil: Char = 'C'
    val mayorEdad: Boolean = true
    //Clases Java
    val fechaNacimiento: Date = Date()

    //Condicionales

    //Switch
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("Sin especificar")
        }
    }

    val esSoltero = (estadoCivilWhen == "s")
    val coqueteo = if(esSoltero) "Si" else "No"}

    //void -> Unit
    fun imprimirNombre(nombre: String): Unit{
        println("Nombre : ${nombre}") //template string
    }

    fun calcularSueldo(
        sueldo: Double, //requeerido
        tasa: Double = 12.00, //opcionar (defecto)
        bonoEspecial: Double? = null, //opcion null -> nullable
    ): Double{
        //int -> int?(nulleable)
        //String -> String?(nulleable)
        if(bonoEspecial == null){
            return sueldo * (100/tasa)
        }else{
            return sueldo*(100/tasa)+bonoEspecial
        }
    }
}