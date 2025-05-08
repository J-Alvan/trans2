package com.example.swifttrans.ui.theme.screens.trips

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.swifttrans.data.TripViewModel
import com.example.swifttrans.models.Trip
import com.example.swifttrans.ui.theme.screens.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripsScreen(navController: NavController, viewModel: TripViewModel) {
    val trips by viewModel.trips.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Trips") })
        }
    ) { padding ->
        if (trips.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No trips booked yet.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                items(trips) { trip ->
                    TripCard(trip)
                }
            }
        }
    }
}

@Composable
fun TripCard(trip: Trip) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${trip.name}", fontWeight = FontWeight.Bold)
            Text("Route: ${trip.route}")
            Text("Date: ${trip.date}")
            Text("Phone: ${trip.phone}")
            Text("ID: ${trip.id}")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TripsScreenPreview() {
    TripsScreen(
        navController = rememberNavController(),
        viewModel = viewModel()
    )
}


