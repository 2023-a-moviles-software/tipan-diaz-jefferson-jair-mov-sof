import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class JsonFile {
    private val gson = Gson()
    private val FILENAME: String = "data.json"
    private val file = File(FILENAME)


    // Save a list of user in JsonFile
    fun saveList(dataList: ArrayList<Conjunto>) {
        val jsonData = gson.toJson(dataList)
        file.writeText(jsonData)
    }

    // Load a list if user from JsonFile
    fun loadList(): ArrayList<Conjunto> {
        val jsonData = file.readText()
        val type = object : TypeToken<List<Conjunto>>() {}.type
        try {
            return gson.fromJson<ArrayList<Conjunto>>(jsonData, type)
        } catch (e: NullPointerException) {
            return ArrayList()
        }

    }

    fun createDepartamento(conjunto: Conjunto){
        val listOfUser = loadList()
        listOfUser.add(conjunto)
        saveList(listOfUser)
    }

    fun isConjuntoInFile(id: String): Boolean {
        val listOfConjuntos: ArrayList<Conjunto> = loadList()
        var conjunto: Conjunto? = null
        var isFind = false
        for (conjuntoList: Conjunto in listOfConjuntos) {
            if (conjuntoList.id == id) {
                isFind = true
            }
        }
        return isFind

    }

    fun insertUser(conjunto: Conjunto) {
        val listOfConjuntos: ArrayList<Conjunto> = loadList()
        if (!isConjuntoInFile(conjunto.id)) {
            listOfConjuntos.add(conjunto)
            saveList(listOfConjuntos)
        }
    }

    fun getAllUser(): ArrayList<Conjunto> {
        return loadList()
    }

    fun getConjuntoById(id: String): Conjunto? {
        val listOfUsers = loadList()
        var conjunto: Conjunto? = null
        for (conjuntoList: Conjunto in listOfUsers) {
            if (conjuntoList.id == id) {
                conjunto = conjuntoList
                break
            }
        }

        return conjunto;
    }


    fun updateDepartamento(conjunto: Conjunto): Boolean {
        val listOfConjuntos: ArrayList<Conjunto> = loadList()
        var conjuntoFind: Conjunto? = null
        var isUpdate: Boolean = false
        if (isConjuntoInFile(conjunto.id)) {
            conjuntoFind = getConjuntoById(conjunto.id)
            if (conjuntoFind != null) {
                listOfConjuntos.remove(conjuntoFind)
                listOfConjuntos.add(conjunto)
                saveList(listOfConjuntos)
                isUpdate = true
            } else {
                isUpdate = false
            }
        }
        return isUpdate
    }

    fun removeConjunto(conjunto: Conjunto): Boolean {
        val listOfUsers = loadList()
        var isRemove = false
        if (isConjuntoInFile(conjunto.id)) {
            listOfUsers.remove(conjunto)
            saveList(listOfUsers)
            isRemove = true
        }
        return isRemove
    }

    fun removeUserById(id: String): Boolean {
        val listOfConjunto: ArrayList<Conjunto> = loadList()
        var isRemove = false
        if (isConjuntoInFile(id)) {
            val conjunto: Conjunto? = getConjuntoById(id)
            listOfConjunto.remove(conjunto)
            isRemove = true
            saveList(listOfConjunto)
        }

        return isRemove
    }

    fun getListOfDepartamentoByUserId(id: String): ArrayList<Departamento> {
        val listOfConjunto = loadList()
        var listOfDepartamentos: ArrayList<Departamento> = ArrayList()
        for (conjunto: Conjunto in listOfConjunto) {
            if (conjunto.id == id) {
                listOfDepartamentos = conjunto.listOfDepartamento
                break
            }
        }
        return listOfDepartamentos
    }

    fun getTorreByDepartamento(conjunto: Conjunto, license: String): Departamento?{
        val listOfCars = getListOfDepartamentoByUserId(conjunto.id)
        var departamentoReturn: Departamento? = null
        for (departamento: Departamento in listOfCars){
            if( departamento.torreDepartamento == license ) {
                departamentoReturn = departamento
                break
            }

        }
        return departamentoReturn
    }

    fun updateDepartamentoByTorre(conjunto: Conjunto, departamento: Departamento) {
        val listOfDepartamentos = getListOfDepartamentoByUserId(conjunto.id)
        val DepartamentoFinding = getTorreByDepartamento(conjunto, departamento.torreDepartamento)
        listOfDepartamentos.remove(DepartamentoFinding)
        listOfDepartamentos.add(departamento)
        conjunto.listOfDepartamento = listOfDepartamentos
        updateDepartamento(conjunto)
    }

    fun deleteDepartamentoById(conjunto: Conjunto, departamento: Departamento){
        val listOfDepartamento = getListOfDepartamentoByUserId(conjunto.id)
        val carFinding = getTorreByDepartamento(conjunto, departamento.torreDepartamento)
        listOfDepartamento.remove(carFinding)
        conjunto.listOfDepartamento = listOfDepartamento
        updateDepartamento(conjunto)

    }






}