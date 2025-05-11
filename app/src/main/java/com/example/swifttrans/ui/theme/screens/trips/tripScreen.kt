package com.example.swifttrans.ui.theme.screens.trips


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.swifttrans.models.Trip
import com.example.swifttrans.data.TripViewModel
import com.example.swifttrans.navigation.ROUTE_ROUTES
import com.example.swifttrans.ui.theme.screens.profile.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripsScreen(navController: NavController) {
    val viewModel: TripViewModel = viewModel()
    val context = LocalContext.current
    val tripList by viewModel.tripList.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var tripToDelete by remember { mutableStateOf<Trip?>(null) }



    LaunchedEffect(Unit) {
        viewModel.fetchTrips()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Trips") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(tripList) { trip ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Name: ${trip.name}")
                            Text("Phone: ${trip.phone}")
                            Text("ID: ${trip.idNumber}")
                            Text("Date: ${trip.scheduleDate}")
                            Text("Route: ${trip.routeName}")
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(onClick = {
                                    navController.navigate(ROUTE_ROUTES)
                                    Toast.makeText(context, "Update Trip", Toast.LENGTH_SHORT)
                                        .show()
                                }) {
                                    Icon(Icons.Filled.Edit, contentDescription = "Update")
                                }
                                IconButton(onClick = {
                                    tripToDelete = trip
                                    showDeleteDialog = true
                                }) {
                                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                                }
                            }
                        }
                    }
                }
            }
        }
    }


               if (showDeleteDialog && tripToDelete != null) {
                AlertDialog(
                    onDismissRequest = { showDeleteDialog = false },
                    title = { Text("Delete Trip") },
                    text = { Text("Are you sure you want to delete this trip?") },
                    confirmButton = {
                        TextButton(onClick = {
                            tripToDelete?.tripId?.let { viewModel.deleteTrip(it) }
                            Toast.makeText(context, "Trip deleted", Toast.LENGTH_SHORT).show()
                            showDeleteDialog = false
                        }) {
                            Text("Delete")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDeleteDialog = false
                        }) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TripScreenPreview() {
    TripsScreen(navController = NavController(LocalContext.current))
}