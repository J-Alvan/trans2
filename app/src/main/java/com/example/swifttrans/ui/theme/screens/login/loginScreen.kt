package com.example.swifttrans.ui.theme.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.swifttrans.R
import com.example.swifttrans.navigation.ROUTE_REGISTER
import com.example.swifttrans.data.AuthViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val authViewModel: AuthViewModel = viewModel()
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isLoading by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bus),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(
                text ="Swift Trans",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimary) })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        )  {
            // Logo and Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "Swift Trans Logo",
                        modifier = Modifier
                            .size(48.dp)
                            .padding(end = 8.dp)
                    )
                Text(
                    text = "Login Here",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontSize = 36.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))


            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    authViewModel.login(email, password, navController, context)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text("Login")
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(onClick = {
                navController.navigate("register")
            }) {
                Text(
                    text = buildAnnotatedString { append("Don't have an account? Register Here ") },
                    modifier = Modifier.wrapContentWidth().clickable {
                        navController.navigate(ROUTE_REGISTER)
            })
        }
    }
}}}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(LocalContext.current))
}