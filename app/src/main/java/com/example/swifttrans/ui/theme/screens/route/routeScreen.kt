package com.example.swifttrans.ui.theme.screens.route

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

data class Route(
    val id: String,
    val name: String,
    val pickupPoint: String,
    val destinationPoint: String,
    val price: Double,
    val departureTime: String
)





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteScreen(navController: NavController) {
    val context = LocalContext.current
    val routes = listOf(
        Route("1", "Nairobi to Kisumu", "Nairobi CBD", "Kisumu Town", 1000.0,"9.30pm"),
        Route("2", "Kisumu to Kakamega", "Downtown Plaza", "University Campus", 900.0, "6.45pm"),
        Route("3", "Mombasa to Nairobi", "Harbor Point", "Nairobi CBD", 1200.0, "12.00pm"),
        Route("4", "Nairobi to Meru", "Nairobi CBD", "Summit View", 600.0, "8.00am"),
        Route("5", "Nakuru to Kirinyaga", "City Center", "Shopping District", 800.0, "4.00pm")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Swift Trans Routes") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Select a route to book",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 16.dp), textAlign = TextAlign.Center
                )
            }
            items(routes) { route ->
                RouteCard(
                    route = route,
                    onRouteClick = {
                        // Navigate to booking screen with route ID
                        navController.navigate("booking/${route.id}")
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteCard(route: Route, onRouteClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRouteClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = route.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Pick-Up:",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = route.pickupPoint,
                        fontSize = 16.sp
                    )
                }

                Column {
                    Text(
                        text = "Drop-off:",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = route.destinationPoint,
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dept. time: ${route.departureTime}",
                    fontSize = 14.sp
                )

                Text(
                    text = "Ksh${String.format("%.2f", route.price)}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RouteScreenPreview(){
    RouteScreen(navController = NavController(LocalContext.current))

}
