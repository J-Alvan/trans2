package com.example.swifttrans.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swifttrans.ui.theme.screens.SplashScreen
import com.example.swifttrans.ui.theme.screens.booking.BookingScreen
import com.example.swifttrans.ui.theme.screens.home.HomeScreen
import com.example.swifttrans.ui.theme.screens.login.LoginScreen
import com.example.swifttrans.ui.theme.screens.profile.ProfileScreen
import com.example.swifttrans.ui.theme.screens.register.RegisterScreen
import com.example.swifttrans.ui.theme.screens.route.RouteScreen


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
        composable(ROUTE_BOOKING) { BookingScreen(navController) }
        composable(ROUTE_PROFILE) { ProfileScreen(navController) }
        composable(ROUTE_HOME) { HomeScreen(navController) }
        composable(ROUTE_BOOKING) { BookingScreen(navController) }

    }
}