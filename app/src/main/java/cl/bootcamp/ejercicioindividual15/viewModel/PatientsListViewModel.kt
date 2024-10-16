package cl.bootcamp.ejercicioindividual15.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cl.bootcamp.ejercicioindividual15.model.PatientsListState

class PatientsListViewModel : ViewModel() {

    var state by mutableStateOf(PatientsListState())
        private set

    var listPatients by mutableStateOf(listOf<PatientsListState>())

    fun onValue(value: String, text: String) {
        when (text) {
            "namePatient" -> state = state.copy(namePatient = value)
            "age" -> state = state.copy(age = value)
            "weight" -> state = state.copy(weight = value)
            "height" -> state = state.copy(height = value)
            "bmi" -> state = state.copy(bmi = value)
        }
    }


    fun PatientsAdd(name: String) {
        val newPatients = state.copy(
            id = listPatients.size + 1,
            name = name
        )

        listPatients += newPatients
    }

    fun PatientsUpdate(idPatient: Int,
                       agePatient: String,
                       weightPatient: String,
                       heightPatient: String,
                       bmiPatient: String,
                       bmiStatusPatient: String) {

        val itemEdit = listPatients.find { it.id == idPatient }

        itemEdit?.let {
            it.age = agePatient
            it.height = heightPatient
            it.weight = weightPatient
            it.bmi = bmiPatient
            it.bmiStatus = bmiStatusPatient
        }

    }

    fun cleanStateCalculate() {
        state = state.copy(namePatient = "")
    }

    fun openModal() {
        state = state.copy(flagModal = true)
    }

    fun closeModal() {
        state = state.copy(flagModal = false)
    }

    fun cleanState() {
        state = state.copy(namePatient = "")
    }

    fun calculate() {


        if (state.height != "" && state.weight != "") {
            state = state.copy(
                bmi = calculateBMI(state.weight.toDouble(), state.height.toDouble()).toString()
            )
        } else {
            state = state.copy(flagAlert = true)
        }

        /*if (state.height != "") {
            state = state.copy(flagHeight = true)
        }
        if (state.weight != "") {
            state = state.copy(flagWeight = true)
        }*/
    }

    // funcion que calcula el bmi
    fun calculateBMI(weightKG: Double, heightCM: Double): Double {
        val heightM = heightCM / 100  // transformo de cm a mt
        val result = weightKG / (heightM * heightM)
        return kotlin.math.round(result * 100) / 100.0
    }

    fun closeAlert() {
        state = state.copy(flagAlert = false)
    }

    // funcion genera descripcion de estado de salud
    fun calculateHealthStatus(bmi: Double): String {
        return when (bmi) {
            in 1.0..18.5 -> "Bajo peso"
            in 18.5 ..24.9 -> "Peso normal"
            in 25.0 ..29.9 -> "Pre-obesidad"
            in 30.0 ..34.9 -> "Obesidad clase I"
            in 35.0 ..39.9 -> "Obesidad clase II"
            else -> "Obesidad clase III"
        }
    }

}