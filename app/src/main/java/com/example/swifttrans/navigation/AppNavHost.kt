package com.example.swifttrans.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swifttrans.data.TripViewModel
import com.example.swifttrans.models.Trip
import com.example.swifttrans.ui.theme.screens.SplashScreen
import com.example.swifttrans.ui.theme.screens.booking.BookingScreen
import com.example.swifttrans.ui.theme.screens.home.HomeScreen
import com.example.swifttrans.ui.theme.screens.login.LoginScreen
import com.example.swifttrans.ui.theme.screens.profile.ProfileScreen
import com.example.swifttrans.ui.theme.screens.register.RegisterScreen
import com.example.swifttrans.ui.theme.screens.review.ReviewScreen
import com.example.swifttrans.ui.theme.screens.route.Route
import com.example.swifttrans.ui.theme.screens.route.RouteScreen
import com.example.swifttrans.ui.theme.screens.rules.RulesScreen
import com.example.swifttrans.ui.theme.screens.trips.TripsScreen


@Composable
fun AppNavHost(navController:NavHostController= rememberNavController(),startDestination:String= ROUTE_SPLASH){
    NavHost(navController=navController,startDestination=startDestination){
        composable(ROUTE_SPLASH){ SplashScreen{
                navController.navigate(ROUTE_REGISTER){
                    popUpTo(ROUTE_SPLASH){inclusive=true}} }
        }
        composable(ROUTE_ROUTES) { RouteScreen(navController) }
        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_LOGIN) { LoginScreen(navController) }
        composable("booking/{routeId}") { backStackEntry ->
            val routeId = backStackEntry.arguments?.getString("routeId") ?: ""
            BookingScreen(routeId = routeId, navController = navController)
        }
        composable(ROUTE_PROFILE) { ProfileScreen(navController) }
        composable(ROUTE_HOME) { HomeScreen(navController) }
        composable(ROUTE_TRIP) { TripsScreen(navController) }
        composable(ROUTE_REVIEW) { ReviewScreen(navController) }
        composable(ROUTE_RULES) { RulesScreen(onBackClick = {
            navController.popBackStack()
        }) }

    }
}