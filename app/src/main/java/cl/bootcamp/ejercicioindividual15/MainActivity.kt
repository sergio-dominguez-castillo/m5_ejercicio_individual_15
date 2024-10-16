package cl.bootcamp.ejercicioindividual15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import cl.bootcamp.ejercicioindividual15.navigation.NavManager
import cl.bootcamp.ejercicioindividual15.ui.theme.EjercicioIndividual15Theme
import cl.bootcamp.ejercicioindividual15.viewModel.PatientsListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: PatientsListViewModel by viewModels()
        setContent {
            EjercicioIndividual15Theme {
                NavManager(viewModel)
            }
        }
    }
}

