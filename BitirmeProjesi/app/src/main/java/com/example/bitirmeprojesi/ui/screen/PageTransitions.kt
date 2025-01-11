package com.example.bitirmeprojesi.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bitirmeprojesi.data.entity.Movie_Cart
import com.example.bitirmeprojesi.data.entity.Movies
import com.example.bitirmeprojesi.ui.viewmodel.HomeViewModel
import com.google.gson.Gson

@Composable
fun PageTransitions(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homepage") {
        composable("homepage") {
            HomePage(navController = navController, homeViewModel,
                onsepetClick = {
                }
            )
        }

        composable(
            "cartPage/{cartId}/{image}/{name}/{price}/{desc}",
            arguments = listOf(
                navArgument("cartId") { type = NavType.IntType },
                navArgument("image") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType },
                navArgument("desc") { type = NavType.StringType },
                navArgument("price") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val cartId = backStackEntry.arguments?.getInt("cartId")
            val image = backStackEntry.arguments?.getString("image")
            val name = backStackEntry.arguments?.getString("name")
            val desc = backStackEntry.arguments?.getString("desc")

            val price = backStackEntry.arguments?.getFloat("price")

            CartPage(cartId = cartId, image = image, name = name, price = price,desc= desc ,onBackClick = {navController.popBackStack()
            })
        }


        composable("detailpage/{movie}",
            arguments = listOf(navArgument("movie") {
                type = NavType.StringType
            }
            )) {
            val json = it.arguments?.getString("movie")
            val data = Gson().fromJson(json, Movies::class.java)
            DetailPage(data,
                onBackClick = {
                    navController.navigate("homepage")
                },
                onCartClick = {   },
                onListClick={
                    navController.navigate("listpage")
                },
                navController = navController
            )

        }


    }
}
