package cl.bootcamp.ejercicioindividual15.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.ejercicioindividual15.dataStore.StoreBoarding
import cl.bootcamp.ejercicioindividual15.onBoardings.MainOnBoarding
import cl.bootcamp.ejercicioindividual15.view.CalculateView
import cl.bootcamp.ejercicioindividual15.view.HomeView
import cl.bootcamp.ejercicioindividual15.viewModel.PatientsListViewModel
import cl.bootcamp.ejercicioindividual15.view.SplashScreen

@Composable
fun NavManager(viewModel: PatientsListViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val dataStore = StoreBoarding(context)
    val store = dataStore.getBoarding.collectAsState(initial = true)

    NavHost(navController, startDestination = if (store.value) "Home" else "Splash") {
        composable("OnBoarding") {
            MainOnBoarding(navController, dataStore)
        }

        composable("Home") {
            HomeView(navController, viewModel)
        }
        composable("Splash") {
            SplashScreen(navController, store.value)
        }
        composable("Calculate/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            //navArgument("nombre") { type = NavType.StringType }
        )) {
            val id = it.arguments?.getInt("id") ?: 0
            CalculateView(navController,viewModel, id )
        }
    }
}