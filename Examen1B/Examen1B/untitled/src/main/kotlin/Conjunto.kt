data class Conjunto(
    var id: String,
    var nombre: String = "No  name",
    var numDepartamentos: Int = 0,
    var mensualidad: Double = 0.0,
    var disponible: Boolean,
    var propietario: Char = 'S',
    var listOfDepartamento: ArrayList<Conjunto> = arrayListOf<Conjunto>()
) {

    override fun toString(): String {
        var salida = "Id: '$id'\n" +
                "Nombre del conjunto: '$nombre'\n" +
                "Numero de departamentos: $numDepartamentos\n" +
                "Mensualidades: $mensualidad\n" +
                "Hay departamentos disponibles?: $disponible\n" +
                "Es propietario de algun departamento?: $propietario\n" +
                "Lista de departamentos:\n"
        for (departamento in listOfDepartamento) {
            salida += departamento
        }
        return salida
    }
}