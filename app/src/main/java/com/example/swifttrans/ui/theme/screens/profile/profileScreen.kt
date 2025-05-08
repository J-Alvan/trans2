package com.example.swifttrans.ui.theme.screens.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.swifttrans.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(navController: NavController) {
    val user = FirebaseAuth.getInstance().currentUser
    val context = LocalContext.current

    var name by remember { mutableStateOf(user?.displayName ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var phone by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Profile Image with click to pick from gallery
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .clickable { imagePickerLauncher.launch("image/*") },
            contentAlignment = Alignment.Center
        ) {
            if (selectedImageUri != null) {
                AsyncImage(
                    model = selectedImageUri,
                    contentDescription = "Selected Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = "Default Profile",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("User Profile", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(24.dp))

        if (isEditing) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { isEditing = false }) {
                Text("Save")
            }
        } else {
            ProfileRow("Name", name)
            ProfileRow("Email", email)
            ProfileRow("Phone", phone)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { isEditing = true }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
                Text("Edit", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { showDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
        ) {
            Icon(Icons.Default.Close, contentDescription = "Logout", tint = MaterialTheme.colorScheme.onError)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign Out", color = MaterialTheme.colorScheme.onError)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirm Sign Out") },
            text = { Text("Are you sure you want to sign out?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        FirebaseAuth.getInstance().signOut()
                        showDialog = false
                        navController.navigate("login") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                ) {
                    Text("Sign Out")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun ProfileRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(label, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(value, fontSize = 14.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = NavController(LocalContext.current))
}
