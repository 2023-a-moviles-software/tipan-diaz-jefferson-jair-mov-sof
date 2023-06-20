import java.util.*

class Menu {
    private val scanner = Scanner(System.`in`)
    private val jsonFile = JsonFile()


    fun seleccionarInicioMensaje() {
        println(
            "APLICACIÓN DE UN CONJUNTO HABITACIONAL \n" +
                    "Seleccione una opcion del siguiente menú:\n"
        )
        val opciones = arrayListOf<String>(
            "Crear conjunto",
            "Actualizar conjunto",
            "Eliminar conjunto por id",
            "Mostrar conjunto por id",
            "Crear, actualizar, eliminar o mostrar departamento",
            "Salir"
        )
        for (i in opciones.indices) {
            println("${i + 1}" + " " + opciones[i])
        }

        when (scanner.next()) {
            "1" -> {
                crearConjunto()
                seleccionarInicioMensaje()
            }

            "2" -> {
                actualizarConjunto("2")
                seleccionarInicioMensaje()
            }

            "3" -> {
                actualizarConjunto("3")
                seleccionarInicioMensaje()
            }

            "4" -> {
                actualizarConjunto("4")
                seleccionarInicioMensaje()
            }

            "5" -> {
                actualizarConjunto("5")
                seleccionarInicioMensaje()
            }
        }

    }

    private fun eliminarConjunto(conjunto: Conjunto?) {
        if (jsonFile.removeConjunto(conjunto!!)) {
            println("Se elimino correctamente el conjunto")
        }
    }

    private fun actualizarConjunto(opcion: String) {
        println("Ingrese id del conjunto")
        val idConjunto = scanner.next()
        if (jsonFile.isConjuntoInFile(idConjunto)) {
            val conjunto = jsonFile.getConjuntoById(idConjunto)
            when (opcion) {
                "2" -> opcionesActualizarDepartamento(conjunto!!)
                "3" -> eliminarConjunto(conjunto!!)
                "4" -> mostraConjunto(conjunto!!)
                "5" -> mostrarCrudDepartamentoDadoConjunto(conjunto!!)
            }

        } else {
            println("El conjuno no existe aun")
        }

    }

    private fun mostraConjunto(conjunto: Conjunto?) {
        println(conjunto.toString())
    }

    private fun opcionesActualizarDepartamento(conjunto: Conjunto?) {
        println("Seleccione que desea actualizar")
        val opciones = arrayListOf<String>("Nombre del conjunto", "Numero de departamentos", "Mensualidades",
            "Hay departamentos disponibles?", "Es propietario de algun departamento")
        for (i in opciones.indices) {
            println("${i + 1}" + " " + opciones[i])
        }
        when (scanner.next()) {
            "1" -> {
                print("Ingrese el nombre del conjunto: Str")
                conjunto!!.nombre = scanner.next()
            }

            "2" -> {
                println("Ingrese el numero de departamentos: Int")
                conjunto!!.numDepartamentos = scanner.nextInt()
            }

            "3" -> {
                println("Ingrese la mensualidad: Double")
                conjunto!!.mensualidad = scanner.nextDouble()
            }

            "4" -> {
                println("Es hombre ? (Responsa S para Si o N para no)")
                conjunto!!.disponible = scanner.next() == "S"
            }

            "5" -> {
                println("Es propietario de algun departamento ? (Responsa 'S' para Si o 'N' no)")
                conjunto!!.propietario = if (scanner.next() == "S") 'C' else 'S'
            }
        }
        jsonFile.updateDepartamento(conjunto!!)
    }

    private fun crearConjunto() {
        println("Ingrese el número de cédula de la persona: Str")
        val id = scanner.next()

        if (!jsonFile.isConjuntoInFile(id)) {
            println("Ingrese el nombre: Str")
            val nombre = scanner.next()
            println("Ingrese la edad: Int")
            val edadDepartamento = scanner.nextInt()
            println("Ingrese la mensualidad: Double")
            val mensualidad = scanner.nextDouble()
            println("Esta disponible el departamento ? (Responsa S para Si o N para no)")
            val disponibleOpcion = scanner.next()
            val disponible: Boolean = disponibleOpcion == "S"
            println("Está propietario ? (Responsa 'S' para Si o 'N' no)")
            val propietarioOpcion = scanner.next()
            val propietario: Char = if (propietarioOpcion == "S") 'A' else 'S'
            println("Tiene departamentos: (Responsa 'S' para Si o 'N' no)")
            val tieneDepartamento = scanner.next()
            val conjunto = Conjunto(id, nombre, edadDepartamento, mensualidad, disponible, propietario)
            jsonFile.createDepartamento(conjunto)
            confirmarAniadirDepartamento(tieneDepartamento, conjunto)
        } else {
            println("El departamento ya existe")
        }

    }

    private fun confirmarAniadirDepartamento(tieneDepartamento: String?, conjunto: Conjunto) {
        if (tieneDepartamento == "S") {
            println("Desea ingresar información de su departamento (Responsa 'S' para Si o 'N' no)")
            val mensajeDeConfirmacionAniadirDepartamento = scanner.next()
            aniadirDepartamento(mensajeDeConfirmacionAniadirDepartamento, conjunto)
        }
    }

    private fun aniadirDepartamento(mensajeDeConfirmacionAniadirDepartamento: String?, conjunto: Conjunto) {
        if (mensajeDeConfirmacionAniadirDepartamento == "S") {
            do {
                println("Ingrese el número de departamento: Str")
                val torreDepartamento = scanner.next()
                if(jsonFile.getTorreByDepartamento(conjunto!!, torreDepartamento) == null){
                    println("Ingrese el número de habitaciones de su departamento: Int")
                    val numHabitaciones = scanner.nextInt()
                    println("Ingese el tiempo que tiene de construccion su departamento: Double")
                    val edadDepartamento = scanner.nextDouble()
                    var estaDisponible = false
                    if(edadDepartamento == 0.0){
                        estaDisponible = true
                    }

                    val obtenerTorre: Char = torreDepartamento.get(0)
                    val departamento = Departamento(numHabitaciones, edadDepartamento, estaDisponible, torreDepartamento, obtenerTorre, conjunto.id)
                    conjunto.listOfDepartamento.add(conjunto) // estaba departamento en vez de conjunto
                    jsonFile.updateDepartamento(conjunto)
                }else{
                    println("Existe un departamento con la misma numeracion")
                }

                println("Quieres añadir otro departamento ? (Responsa 'S' para Si o 'N' no)")
            } while (scanner.next() == "S")
            mostrarCrudDepartamentoDadoConjunto(conjunto)
        }

    }

    private fun mostrarCrudDepartamentoDadoConjunto(conjunto: Conjunto) {
        val opciones =
            arrayListOf<String>("Mostrar conjunto/s", "Actualizar conjunto", "Eliminar conjunto", "Crear conjunto", "Salir")
        for (i in opciones.indices) {
            println("${i + 1}" + " " + opciones[i])
        }
        when (scanner.next()) {
            "1" -> {
                println(conjunto.listOfDepartamento)
                mostrarCrudDepartamentoDadoConjunto(conjunto)

            }

            "2" -> {
                actualizarDepartamento(conjunto)
                mostrarCrudDepartamentoDadoConjunto(conjunto)

            }

            "3" -> {
                eliminarDepartamento(conjunto)
                mostrarCrudDepartamentoDadoConjunto(conjunto)

            }

            "4" -> {
                aniadirDepartamento("S", conjunto)
                mostrarCrudDepartamentoDadoConjunto(conjunto)
            }

        }
    }

    private fun eliminarDepartamento(conjunto: Conjunto) {
        println("Introduzca la la torre del departamento")
        val torreDepartamento = scanner.next()
        val departamento = jsonFile.getTorreByDepartamento(conjunto, torreDepartamento)
        val listOfDepartamento = jsonFile.getListOfDepartamentoByUserId(conjunto.id)
        if (departamento != null) {
            listOfDepartamento.remove(departamento)
            //conjunto.listOfDepartamento = listOfDepartamento
            jsonFile.updateDepartamento(conjunto)
        }

    }

    private fun actualizarDepartamento(conjunto: Conjunto) {
        println("Introduzca la numeracion de la Torre")
        val numTorre = scanner.next()
        val departamento = jsonFile.getTorreByDepartamento(conjunto, numTorre)

        if (departamento != null) {
            val opciones = arrayListOf<String>("Numero de puertas", "Recorrido")
            for (i in opciones.indices) {
                println("${i + 1}" + " " + opciones[i])
            }
            println("Seleccione una opcion: ")
            when (scanner.next()) {
                "1" -> {
                    println("Actualice el numero de departamento: Int")
                    departamento.numHabitaciones = scanner.nextInt()
                    jsonFile.updateDepartamentoByTorre(conjunto, departamento)
                }

                "2" -> {
                    println("Actualice el tiempo del departamento: Double")
                    departamento.edadDepartamento = scanner.nextDouble()
                    departamento.estaDisponible = departamento.edadDepartamento == 0.0
                    jsonFile.updateDepartamentoByTorre(conjunto, departamento)
                }
            }

        } else {
            println("La persona aún no tiene departamento en la torre")
        }

    }
}