package com.example.sharedtransitionexample.sharedtransition

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedElementNavGraph(modifier: Modifier = Modifier) {
    val destination1 = "listScreen"
    val destination2 = "detailsScreen"

    val navController = rememberNavController()

    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = destination1,
        ) {
            composable(route = destination1) {
                ListScreen(
                    animatedVisibilityScope = this,
                    onNavigateDetailsPage = { resId , text ->
                        navController.navigate("${destination2}/$resId/$text")
                    }
                )
            }

            composable(
                route = "${destination2}/{resId}/{name}",
                arguments = listOf(
                    navArgument("resId"){
                        type = NavType.IntType
                    },
                    navArgument("name"){
                        type = NavType.StringType
                    }
                )
            ) {
                val resId = it.arguments?.getInt("resId") ?: 0
                val name = it.arguments?.getString("name") ?: ""
                DetailsScreen(
                    item = resId,
                    name = name,
                    animatedVisibilityScope = this
                )
            }
        }
    }
}