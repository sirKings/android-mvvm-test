package co.king.chasetest.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.king.chasetest.planetDetail.presentation.PlanetDetailScreen
import co.king.chasetest.planetList.presentation.PlanetListScreen
import co.king.chasetest.ui.theme.ChaseTestTheme
import co.king.chasetest.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChaseTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.PlanetListScreen.route
                    ) {
                        composable(Screen.PlanetListScreen.route) {
                            PlanetListScreen(navController)
                        }

                        composable(
                            Screen.PlanetDetailScreen.route +"/{$PLANET_ID}",
                            arguments = listOf(
                                navArgument(PLANET_ID) { type = NavType.IntType }
                            )) {
                            PlanetDetailScreen()
                        }
                    }

                }
            }
        }
    }

    companion object{
        const val PLANET_ID = "planetId"
    }
}


