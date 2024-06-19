package trungmvph44499.fpoly.kot104_trungmvph44499.ui.theme.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import trungmvph44499.fpoly.kot104_trungmvph44499.viewmodel.MovieViewModel


enum class Screen(val route: String) {
    MOVIE_SCREEN("MovieScreen"),
    ADD("Add"),
    EDIT("Edit"),
}

@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()
    val movieViewModel = MovieViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.MOVIE_SCREEN.route,
    ) {
        composable(Screen.MOVIE_SCREEN.route) { MovieScreen(navController, movieViewModel) }
        composable(Screen.ADD.route) { MovieFormScreen(navController, movieViewModel, null) }
        composable(
            "${Screen.EDIT.route}/{filmId}",
            arguments = listOf(navArgument("filmId") { type = NavType.StringType }),
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("filmId")?.let { filmId ->
                MovieFormScreen(navController, movieViewModel, filmId)
            }
        }
    }
}