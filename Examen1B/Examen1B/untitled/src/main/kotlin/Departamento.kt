data class Departamento(
    var numHabitaciones: Int,
    var edadDepartamento: Double,
    var estaDisponible: Boolean = (edadDepartamento == 0.0),
    var torreDepartamento: String,
    var obtenerTorre: Char = torreDepartamento.get(0),
    var propietarioID: String = ""
) {


    override fun toString(): String {
        return "\nNumero de Habitaciones: $numHabitaciones " +
                " -> Tiempo que tiene la vivienda: $edadDepartamento " +
                " -> Esta disponible: $estaDisponible " +
                " -> Torre del departamento: '$torreDepartamento' " +
                " -> Inicial de la torre del departamento: ${torreDepartamento.get(0)}\n"
    }


}