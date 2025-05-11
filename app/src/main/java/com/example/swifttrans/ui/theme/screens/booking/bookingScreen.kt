package com.example.swifttrans.ui.theme.screens.booking

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.swifttrans.data.TripViewModel
import com.example.swifttrans.models.Trip
import com.example.swifttrans.navigation.ROUTE_TRIP
import com.example.swifttrans.ui.theme.screens.route.Route
import java.util.Calendar


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(routeId: String, navController: NavController) {
    val route = remember {
        listOf(
            Route("1", "Nairobi to Kisumu", "Nairobi CBD", "Kisumu Town", 1000.0,"7 hrs"),
            Route("2", "Kisumu to Kakamega", "Downtown Plaza", "University Campus", 900.0, "5 hrs"),
            Route("3", "Mombasa to Nairobi", "Harbor Point", "Nairobi CBD", 1200.0, "12hrs"),
            Route("4", "Nairobi to Meru", "Nairobi CBD", "Summit View", 600.0, "5 hrs"),
            Route("5", "Nakuru to Kirinyaga", "City Center", "Shopping District", 800.0, "4 hrs")
        ).find { it.id == routeId }
    }

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var idnumber by remember { mutableStateOf("") }
    val viewModel: TripViewModel = viewModel()
    val paymentState = viewModel.paymentState.collectAsState().value


    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (route == null) {
            Text(
                text = "Route not found",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = route.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "Pick-Up: ${route.pickupPoint}")
            Text(text = "Drop-off: ${route.destinationPoint}")
            Text(text = "Est. Time: ${route.estimatedTime}")
            Text(
                text = "Price: Ksh${String.format("%.2f", route.price)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Divider()

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = null) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = idnumber,
                onValueChange = { idnumber = it },
                label = { Text("ID Number") },
                leadingIcon = { Icon(Icons.Filled.Info, contentDescription = null) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )
            // Booking Date Field (opens DatePickerDialog on click)
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showDatePickerDialog(context) { selectedDate ->
                        date = selectedDate
                    }
                }) {
                OutlinedTextField(
                    value = date,
                    onValueChange = { },
                    label = { Text("Schedule Date") },
                    leadingIcon = { Icon(Icons.Filled.DateRange, contentDescription = null) },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false // disables internal text field interactions but still visible
                )
            }

            LaunchedEffect(paymentState.isSuccess) {
                if (paymentState.isSuccess) {
                    // Save trip to Firebase
                    viewModel.saveTrip(
                        Trip(
                            tripId = "", // Firebase assigns this
                            name = name,
                            phone = phone,
                            idNumber = idnumber,
                            scheduleDate = date,
                            routeName = route.name,
                            pickup = route.pickupPoint,
                            destination = route.destinationPoint,
                            price = route.price
                        )
                    )

                    // Navigate after successful payment and saving trip
                    navController.navigate("home") {
                    }
                }
            }


            Button(
                onClick = {
                    // Validate required fields
                    if (name.isBlank() || phone.isBlank() || date.isBlank() || idnumber.isBlank()) {
                        Toast.makeText(context, "Please fill in all required fields", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    // Initiate M-Pesa payment
                    viewModel.initiatePayment(
                        phone = phone,
                        amount = route.price,
                        reference = "Trip#${route.id}",
                        description = "Payment for ${route.name}"
                    )
//
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = !viewModel.paymentState.value.isLoading // Disable button while loading
            ) {

                if (viewModel.paymentState.value.isLoading) {
                    Text("Processing Payment...")
                } else {
                    Text("Pay with M-Pesa")
                }
            }

// Add this section to display payment status
            val paymentState = viewModel.paymentState.collectAsState()

            if (paymentState.value.paymentStatus.isNotEmpty()) {
                Text(
                    text = paymentState.value.paymentStatus,
                    color = if (paymentState.value.isSuccess) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            if (paymentState.value.errorMessage.isNotEmpty()) {
                Text(
                    text = paymentState.value.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

           {
            }
        }
    }
}

// Launches a DatePicker and sets a callback with the formatted date string
fun showDatePickerDialog(context: Context, onDateSelected: (String) -> Unit) {
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            onDateSelected("$dayOfMonth/${month + 1}/$year")
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Prevent past dates
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        datePickerDialog.datePicker.minDate = calendar.timeInMillis
    }

    datePickerDialog.show()
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BookingScreenPreview() {
    val navController = rememberNavController()
    BookingScreen(routeId = "1", navController = navController)
}

