package com.trakky.app.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.trakky.app.data.SampleData
import com.trakky.app.ui.components.*
import com.trakky.app.ui.theme.*

@Composable
fun HomeScreen(onOpenSalon: (String) -> Unit, onOpenNearby: () -> Unit) {
    val infinite = rememberInfiniteTransition(label = "hero")
    val pulse by infinite.animateFloat(
        0.95f, 1.05f, infiniteRepeatable(tween(1800, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "pulse"
    )

    var selected by remember { mutableStateOf("Haircut") }

    AnimatedAuroraBackground(Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 56.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Column {
                    Text("Hello, gorgeous ✨", color = RoseGold, style = MaterialTheme.typography.labelLarge)
                    Spacer(Modifier.height(4.dp))
                    Text("Find your perfect", color = Ivory, style = MaterialTheme.typography.headlineMedium)
                    Text(
                        "Salon Experience",
                        style = MaterialTheme.typography.displayLarge,
                        color = Gold,
                        modifier = Modifier.graphicsLayer { scaleX = pulse; scaleY = pulse }
                    )
                }
            }

            // Hero card
            item {
                GoldBorder(Modifier.fillMaxWidth().height(180.dp)) {
                    Box(Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = "https://images.unsplash.com/photo-1503951914875-452162b0f3f1?w=1200",
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop
                        )
                        Box(
                            Modifier.fillMaxSize().background(
                                Brush.horizontalGradient(listOf(Onyx.copy(alpha = 0.85f), Color.Transparent))
                            )
                        )
                        Column(
                            Modifier.padding(20.dp).align(Alignment.CenterStart)
                        ) {
                            Text("✂️  Premium salons near you", color = Gold, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(8.dp))
                            Text("Book instantly", color = Ivory, style = MaterialTheme.typography.titleLarge)
                            Spacer(Modifier.height(12.dp))
                            Button(
                                onClick = onOpenNearby,
                                colors = ButtonDefaults.buttonColors(containerColor = Gold, contentColor = Onyx),
                                shape = RoundedCornerShape(50)
                            ) { Text("Salons near me", fontWeight = FontWeight.Bold) }
                        }
                    }
                }
            }

            // Service pills
            item {
                Text("Services", color = Ivory, style = MaterialTheme.typography.titleLarge)
            }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(SampleData.services) { s ->
                        ServicePill(s.name, s.icon, selected == s.name) { selected = s.name }
                    }
                }
            }

            // Cities
            item {
                Text("Popular Cities", color = Ivory, style = MaterialTheme.typography.titleLarge)
            }
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(SampleData.cities) { (city, img) ->
                        Box(
                            Modifier.size(140.dp, 90.dp)
                                .clip(RoundedCornerShape(16.dp))
                        ) {
                            AsyncImage(
                                model = img, contentDescription = city,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop
                            )
                            Box(Modifier.fillMaxSize().background(
                                Brush.verticalGradient(listOf(Color.Transparent, Onyx.copy(alpha = 0.85f)))
                            ))
                            Text(
                                city, color = Ivory,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.BottomStart).padding(10.dp)
                            )
                        }
                    }
                }
            }

            // Featured salons
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Featured Salons", color = Ivory, style = MaterialTheme.typography.titleLarge)
                    Spacer(Modifier.weight(1f))
                    Text("See all", color = Gold)
                }
            }
            items(SampleData.salons) { s ->
                SalonCard(s) { onOpenSalon(s.id) }
            }
        }
    }
}
