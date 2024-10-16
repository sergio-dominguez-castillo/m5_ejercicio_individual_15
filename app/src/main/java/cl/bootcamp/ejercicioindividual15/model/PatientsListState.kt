package cl.bootcamp.ejercicioindividual15.model

data class PatientsListState (
    var id: Int = 0,
    val name: String = "",
    var namePatient: String = "",
    val flagModal: Boolean = false,
    var age: String = "",
    var weight: String = "",
    var height: String = "",
    var bmi: String = "",
    var bmiStatus: String = "",
    val flagAlert: Boolean = false,
    val flagAge: Boolean = false,
    val flagWeight: Boolean = false,
    var flagHeight: Boolean = false,
    val flagGender: Boolean = false


)