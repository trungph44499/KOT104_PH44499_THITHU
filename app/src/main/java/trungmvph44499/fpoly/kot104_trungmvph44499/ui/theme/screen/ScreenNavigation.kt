package trungmvph44499.fpoly.kot104_trungmvph44499.ui.theme.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import trungmvph44499.fpoly.kot104_trungmvph44499.viewmodel.ItemViewModel


enum class Screen(val route: String) {
    MOVIE_SCREEN("MovieScreen"),
    ADD("Add"),
    EDIT("Edit"),
//    Detail("Detail")

}

@Composable
fun ScreenNavigation() {
    val navController = rememberNavController()
    val itemViewModel = ItemViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.MOVIE_SCREEN.route,
    ) {
        composable(Screen.MOVIE_SCREEN.route) { MovieScreen(navController, itemViewModel) }
        composable(Screen.ADD.route) { MovieFormScreen(navController, itemViewModel, null) }
        composable(
            "${Screen.EDIT.route}/{filmId}",
            arguments = listOf(navArgument("filmId") { type = NavType.StringType }),
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("filmId")?.let { filmId ->
                MovieFormScreen(navController, itemViewModel, filmId)
            }

        }
//        composable("${Screen.Detail.route}/{filmId}/{duration}/{releaseDate}/{genre}/{national}/{description}/{image}"){
//            DetailScreen(
//                navController,
//                filmId = it.arguments?.getString("filmId"),
//                duration = it.arguments?.getInt("duration")!!,
//                releaseDate = it.arguments?.getString("releaseDate")!!,
//                genre = it.arguments?.getString("genre")!!,
//                national = it.arguments?.getString("national")!!,
//                description = it.arguments?.getString("description")!!,
//                image = it.arguments?.getString("image")!!,
//            )
//        }
    }
}