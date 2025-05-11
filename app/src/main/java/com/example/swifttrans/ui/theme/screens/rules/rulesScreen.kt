package com.example.swifttrans.ui.theme.screens.rules


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.swifttrans.ui.theme.SwiftTransTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RulesScreen(
    onBackClick: () -> Unit
) {
    SwiftTransTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Rules & Guidelines", color = Color.DarkGray, textAlign = TextAlign.Center) },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.ic_back),
//                                contentDescription = "Back"
//                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .background(Color(0xFFF5F5F5))
            ) {
                // Banner
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF003366))
                        .padding(vertical = 12.dp)
                ) {
                    Text(
                        text = "SWIFT TRANS - IMPORTANT CLIENT INFORMATION",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer
                        )
                        .padding(vertical = 24.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "SWIFT TRANS RULES & GUIDELINES",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp
                        )
                        Text(
                            text = "Please follow these rules to avoid issues",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                // Rules container
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                ) {
                    // Safety Rules
                    RuleSection(
                        number = 1,
                        title = "Punctuality",
                        rules = listOf(
                            "Bring your goods to the pickup point on time",
                            "Collect your goods at the drop-off point on time",
                        )
                    )

                    Divider()

                    // Fare and Ticketing
                    RuleSection(
                        number = 2,
                        title = "Digital Tickets & Payment",
                        rules = listOf(
                            "Ensure you show your payment before leaving your good",
                            "Ensure you carry your national ID",
                            "Screenshot tickets are not valid and will be rejected",
                            "Subscription passes must be linked to your account",
                            "Payment must be completed before submitting goods",
                            "Route changes may require fare adjustment in-app"
                        )
                    )

                    Divider()

                    // Conduct and Behavior


                    Divider()

                    // Accessibility and Priority Seating
                    RuleSection(
                        number = 3,
                        title = "Respect of Property",
                        rules = listOf(
                            "Do not pick a luggage that is not yours",
                            "Clear with the management before leaving at both pick-up and drop-off points",
                            "Respect to the company and its environs should be observed always",
                        )
                    )

                    Divider()

                    // Items and Luggage
                    RuleSection(
                        number = 4,
                        title = "Luggage",
                        rules = listOf(
                            "No illegal items such as drugs will be transported",
                            "Luggage must not block aisles, doors, or emergency exits",
                            "No pet is transported by the company",
                            "No hazardous, flammable, or offensive goods",
                            "Report lost luggage to the authority"
                        )
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFFFEBEE))
                            .padding(16.dp)
                    ) {
                        Column {
                            Text(
                                text = "IMPORTANT NOTICE:",
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFD32F2F)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Swift Trans is not responsible for lost or stolen items. All transit rules are enforced through our automated monitoring system.",
                                color = Color(0xFF333333)
                            )
                        }
                    }
                }

//                // Real-time updates box
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color(0xFFE3F2FD)
//                    )
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
////                        Icon(
////                            painter = painterResource(id = R.drawable.ic_notifications),
////                            contentDescription = "Notifications",
////                            tint = MaterialTheme.colorScheme.primary,
////                            modifier = Modifier.size(48.dp)
////                        )
//                        Spacer(modifier = Modifier.width(16.dp))
//                        Column {
//                            Text(
//                                text = "Enable Push Notifications",
//                                fontWeight = FontWeight.Bold
//                            )
//                            Text(
//                                text = "Get real-time alerts about delays, schedule changes, and service disruptions",
//                                fontSize = 14.sp
//                            )
//                        }
//                    }
//                }

                // Footer
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "For complete terms and conditions, visit our webpage",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Customer Service: 1-800-SWIFT-TR (1-800-794-3887)",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "App version 2.4.1 • Rules updated: May 2025",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RuleSection(
    number: Int,
    title: String,
    rules: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = number.toString(),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primaryContainer
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            rules.forEach { rule ->
                Row(
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .padding(top = 6.dp)
                            .background(Color(0xFF666666), CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = rule, color = Color.Black)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RulesScreenPreview() {
    RulesScreen(onBackClick = {})
}