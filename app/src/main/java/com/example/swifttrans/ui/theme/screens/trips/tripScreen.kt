package com.example.swifttrans.ui.theme.screens.trips

//import android.app.Application
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewModelScope
//import androidx.navigation.NavController
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import kotlinx.coroutines.launch
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TripsScreen(navController: NavController) {
//
//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("My Trips") })
//        }
//    ) { padding ->
//        if (task.isEmpty()) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding),
//                contentAlignment = Alignment.Center
//            ) {
//                Text("No trips booked yet.")
//            }
//        } else {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding)
//                    .padding(16.dp)
//            ) {
//                items(t) { trip ->
//                    TripCard(trip)
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun TripCard(trip: Trip) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp),
//        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text("Name: ${trip.name}", fontWeight = FontWeight.Bold)
//            Text("Route: ${trip.route}")
//            Text("Date: ${trip.date}")
//            Text("Phone: ${trip.phone}")
//            Text("ID: ${trip.userId}")
//        }
//    }
//}
