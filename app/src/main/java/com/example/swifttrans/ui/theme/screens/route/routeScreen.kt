package com.example.swifttrans.ui.theme.screens.route

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.swifttrans.R
import com.example.swifttrans.navigation.ROUTE_BOOKING
import com.example.swifttrans.navigation.ROUTE_HOME
import com.example.swifttrans.navigation.ROUTE_PROFILE
import com.example.swifttrans.navigation.ROUTE_ROUTES

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RouteScreen(navController: NavController) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    val items = listOf("Home", "Routes", "Book", "Profile")
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }


    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column(modifier = Modifier.fillMaxSize()) {

            // Top App Bar
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Swift Trans", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                },
                actions = {
                    TextButton(onClick = {
                        navController.navigate("booking")
                    }) {
                        Text("Book", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1565C0),
                    titleContentColor = Color.White
                )
            )

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search routes") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Routes List
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                repeat(5) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable {
                                navController.navigate("booking")
                            },
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Route ${it + 1}", fontWeight = FontWeight.Bold)
                            Text("Nairobi to Kisumu")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom Navigation Bar
            NavigationBar(containerColor = Color(0xFF1565C0)) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            when (item) {
                                "Home" -> Icon(Icons.Default.Home, contentDescription = item)
                                "Routes" -> Icon(Icons.Default.Place, contentDescription = item)
                                "Book" -> Icon(Icons.Default.Add, contentDescription = item)
                                "Profile" -> Icon(Icons.Default.AccountCircle, contentDescription = item)
                            }
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            when (item) {
                                "Home" -> navController.navigate(ROUTE_HOME)
                                "Routes" -> navController.navigate(ROUTE_ROUTES)
                                "Book" -> navController.navigate(ROUTE_BOOKING)
                                "Profile" -> navController.navigate(ROUTE_PROFILE)
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RouteScreenPreview(){
    RouteScreen(navController = NavController(LocalContext.current))

}
