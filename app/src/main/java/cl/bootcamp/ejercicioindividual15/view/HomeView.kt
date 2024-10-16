package cl.bootcamp.ejercicioindividual15.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.bootcamp.ejercicioindividual15.components.CardPatient
import cl.bootcamp.ejercicioindividual15.viewModel.PatientsListViewModel
import cl.bootcamp.ejercicioindividual15.components.MainButton
import cl.bootcamp.ejercicioindividual15.components.MainFloating
import cl.bootcamp.ejercicioindividual15.components.Modal


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: PatientsListViewModel
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Ejercicio Individual 15 - Lista de Pacientes",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            MainFloating(onClick = { viewModel.openModal() })
        }
    ) {
        ContentHomeView(it, navController, viewModel)
    }
}

@Composable
fun ContentHomeView(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: PatientsListViewModel
) {

    // cargo las variables del data model
    val state = viewModel.state
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(viewModel.listPatients) { item ->
                CardPatient(
                    item = item,
                    onClick = {
                        navController.navigate("Calculate/${state.id}")
                    }
                )
            }
        }

    }

    if (state.flagModal) {
        Modal(
            title = "Nuevo Paciente",
            onDismissClick = { viewModel.closeModal() },
            onText = {
                Column {
                    OutlinedTextField(
                        value = state.namePatient,
                        onValueChange = { viewModel.onValue(it, "namePatient") },
                        label = { Text(text = "Paciente") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                }
            },
            onConfirmClick = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MainButton(
                        text = "Cerrar"
                    ) { viewModel.closeModal() }

                    MainButton(
                        text = "Agregar"
                    ) {
                        if (state.namePatient.isNotBlank()) {
                            viewModel.PatientsAdd(
                                state.namePatient
                            )
                        }
                        viewModel.closeModal()
                        viewModel.cleanState()
                    }
                }
            }
        )
    }

}
