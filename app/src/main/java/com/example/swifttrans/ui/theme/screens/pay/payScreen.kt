package com.example.swifttrans.ui.theme.screens.pay

//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.swifttrans.data.MpesaViewModel
//import com.example.swifttrans.data.PaymentState
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PaymentScreen(viewModel: MpesaViewModel = hiltViewModel()) {
//    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
//    val paymentState = viewModel.paymentState
//    val fixedAmount = "100" // Fixed amount in KES (modify as needed)
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Pay Here") },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.primary,
//                    titleContentColor = MaterialTheme.colorScheme.onPrimary
//                )
//            )
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//                .padding(horizontal = 16.dp, vertical =16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//        ) {
//        OutlinedTextField(
//            value = phoneNumber,
//            onValueChange = { phoneNumber = it },
//            label = { Text("Phone Number (e.g., 2547XXXXXXXX)") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(
//            text = "Amount to Pay: KES $fixedAmount",
//            style = MaterialTheme.typography.bodyLarge,
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//        Button(
//            onClick = { viewModel.initiatePayment(phoneNumber.text, fixedAmount) },
//            modifier = Modifier.fillMaxWidth(),
//            enabled = paymentState !is PaymentState.Loading
//        ) {
//            Text("Pay Now")
//        }
//        Spacer(modifier = Modifier.height(16.dp))
//        when (paymentState) {
//            PaymentState.Loading -> CircularProgressIndicator()
//            is PaymentState.Success -> Text(
//                text = paymentState.message,
//                color = MaterialTheme.colorScheme.primary
//            )
//            is PaymentState.Error -> Text(
//                text = paymentState.message,
//                color = MaterialTheme.colorScheme.error
//            )
//            PaymentState.Idle -> {}
//        }
//    }
//    }
//}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PayScreenPreview() {
//    PaymentScreen()
//}
