package com.example.swifttrans.ui.theme.screens.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.swifttrans.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.swifttrans.navigation.ROUTE_BOOKING
import com.example.swifttrans.navigation.ROUTE_REVIEW
import com.example.swifttrans.navigation.ROUTE_ROUTES
import com.example.swifttrans.navigation.ROUTE_RULES
import com.example.swifttrans.navigation.ROUTE_TRIP


@Composable
fun HomeScreen(navController: NavController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = { SwiftTransTopAppBar() },
        bottomBar = { SwiftTransBottomNavBar(navController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
            ) {
                WelcomeBanner()
                QuickAccessSection(navController)
                FeaturedSection(navController)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwiftTransTopAppBar() {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Swift Trans Logo",
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Swift Trans",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        },
        actions = {
            IconButton(onClick = {
                val sendIntent = Intent().apply{
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "download here: https://www.download.com")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                context.startActivity(shareIntent)
            }) {
                Icon(Icons.Filled.Share, contentDescription = "Share")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

@Composable
fun SwiftTransBottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("home", "Home", Icons.Filled.Home, Icons.Outlined.Home),
        BottomNavItem(ROUTE_ROUTES, "Book Trip", Icons.Filled.Add, Icons.Outlined.Add),
        BottomNavItem("profile", "Profile", Icons.Filled.Person, Icons.Outlined.Person)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            NavigationBarItem(
                icon = { Icon(if (selected) item.selectedIcon else item.unselectedIcon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = Color.White.copy(alpha = 0.7f),
                    unselectedTextColor = Color.White.copy(alpha = 0.7f),
                    indicatorColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun WelcomeBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        // Background image (replace with your actual background)
        Image(
            painter = painterResource(id = R.drawable.city_background),
            contentDescription = "City Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay with text
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0B3954).copy(alpha = 0.7f))
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                Text(
                    text = "Welcome to Swift Trans",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Your Trusted Cargo Company",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun QuickAccessSection(navController: NavController) {
    val quickAccessItems = listOf(
        QuickAccessItem(ROUTE_ROUTES, "View Routes", Icons.Filled.LocationOn),
        QuickAccessItem( ROUTE_TRIP,"My Trips",Icons.Filled.Add),
        QuickAccessItem(ROUTE_RULES, "Rules", Icons.Filled.Warning),
        QuickAccessItem(ROUTE_REVIEW, "Reviews", Icons.Filled.DateRange),
        QuickAccessItem("contact", "Contact Us", Icons.Filled.Phone),
        QuickAccessItem("email", "Email Us", Icons.Filled.Email),
        )

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Quick Access",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyGrid(items = quickAccessItems, navController = navController)
    }
}

@Composable
fun LazyGrid(items: List<QuickAccessItem>, navController: NavController) {
    Column {
        val context = LocalContext.current
        var itemsProcessed = 0
        while (itemsProcessed < items.size) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in 0..1) {
                    val index = itemsProcessed + i
                    if (index < items.size) {
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            QuickAccessCard(
                                item = items[index],
                                onClick = {
                                    when (items[index].route) {
                                        "contact" -> {
                                            val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
                                                data = Uri.parse("tel:0711343673") // Replace with your actual support number
                                            }
                                            context.startActivity(phoneIntent)
                                        }
                                        "email" -> {
                                            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                                                data = Uri.parse("mailto:ochiengalvan61@gmail.com") // Replace with your actual support email
                                                putExtra(Intent.EXTRA_SUBJECT, "Support Inquiry")
                                                putExtra(Intent.EXTRA_TEXT, "Hello Swift Trans team,\n\n")
                                            }
                                            context.startActivity(Intent.createChooser(emailIntent, "Send Email"))
                                        }
                                        else -> {
                                            navController.navigate(items[index].route)
                                        }
                                    }
                                }

                            )

                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }

                    if (i == 0 && index < items.size - 1) {
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                }
            }
            itemsProcessed += 2
            if (itemsProcessed < items.size) {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

data class QuickAccessItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

@Composable
fun QuickAccessCard(item: QuickAccessItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun FeaturedSection(navController: NavController) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Featured",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.topgear.com/car-news/electric/top-gears-top-20-electric-cars")
                    )
                    context.startActivity(urlIntent)
                }
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cargo_bus),
                        contentDescription = "Electric Bus",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Going Green: Our New Electric Fleet",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Discover how Swift Trans is reducing carbon footprint with our new electric vehicles.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Learn More",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Add more featured content here if needed
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = NavController(LocalContext.current))
}