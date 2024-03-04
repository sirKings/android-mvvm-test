package co.king.chasetest.planetList.presentation

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.king.chasetest.main.MainActivity
import co.king.chasetest.planetDetail.presentation.PlanetDetailScreen
import co.king.chasetest.ui.theme.ChaseTestTheme
import co.king.chasetest.util.Screen
import co.king.chasetest.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PlanetListScreenKtTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            ChaseTestTheme {
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
                            Screen.PlanetDetailScreen.route +"/{${MainActivity.PLANET_ID}}",
                            arguments = listOf(
                                navArgument(MainActivity.PLANET_ID) { type = NavType.IntType }
                            )) {
                            PlanetDetailScreen()
                        }
                    }

                }
            }
        }
    }

    @Test
    fun Given_successful_network_access_home_screen_should_contain_list_of_planets() {
        composeRule.onNodeWithTag(TestTags.PLANET_LIST_TAG).assertExists()
        composeRule.onNodeWithTag(TestTags.PLANET_TITLE_TAG, useUnmergedTree = true).assertExists()
        composeRule.onNodeWithTag(TestTags.PLANET_CLIMATE_TAG, useUnmergedTree = true).assertExists()
    }

    @Test
    fun Given_a_planet_clicked_detail_screen_should_be_displayed(){
        composeRule.onNodeWithTag(TestTags.PLANET_TAG, useUnmergedTree = true).performClick()
        composeRule.onNodeWithTag(TestTags.PLANET_RESIDENT_TAG, useUnmergedTree = true).assertExists()
        composeRule.onNodeWithTag(TestTags.PLANET_FILM_TAG, useUnmergedTree = true).assertExists()
    }
}