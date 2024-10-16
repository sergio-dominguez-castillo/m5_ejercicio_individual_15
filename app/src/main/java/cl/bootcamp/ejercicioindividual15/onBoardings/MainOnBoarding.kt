package cl.bootcamp.ejercicioindividual15.onBoardings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.navigation.NavController
import cl.bootcamp.ejercicioindividual15.R
import cl.bootcamp.ejercicioindividual15.dataStore.StoreBoarding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainOnBoarding(
    navController: NavController,
    store: StoreBoarding
) {
    val items = ArrayList<PageData>()

    items.add(
        PageData(
            R.raw.animation0,
            "Bienvenido!",
            "Es un agrado que utilices nuestra aplicacion de IMC"
        )
    )

    items.add(
        PageData(
            R.raw.animation1,
            "Disfrutar!",
            "utiliza cada funcion y puedes entregarme tus comentarios"
        )
    )

    items.add(
        PageData(
            R.raw.animation2,
            "a Utilizarla!!",
            "enhorabuena, a utilizar la calculadora de IMC"
        )
    )

    val pagerState = rememberPagerState(
        pageCount = items.size,
        initialOffscreenLimit = 2,
        infiniteLoop = false,
        initialPage = 0
    )

    OnBoardingPager(
        item = items,
        pagerState = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
        navController,
        store,
    )
}