package co.king.chasetest.util

sealed class Screen(val route: String) {
    data object PlanetListScreen: Screen("PlanetList")
    data object PlanetDetailScreen: Screen("PlanetDetail")
}