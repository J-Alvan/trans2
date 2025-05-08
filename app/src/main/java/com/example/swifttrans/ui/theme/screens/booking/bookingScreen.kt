package com.example.swifttrans.ui.theme.screens.booking

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var selectedRoute by remember { mutableStateOf("") }
    var travelDate by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }



    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Book a Trip", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1565C0),)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Enter your details", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = selectedRoute,
                onValueChange = { selectedRoute = it },
                label = { Text("Route (e.g., A - B)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = travelDate,
                onValueChange = { travelDate = it },
                label = { Text("Date (e.g., 2025-05-10)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = id,
                onValueChange = { id = it },
                label = { Text("ID Number") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { /* Handle booking logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Confirm Booking")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookingScreenPreview() {
    BookingScreen(navController = NavController(LocalContext.current))
}