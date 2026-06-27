package com.trakky.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.trakky.app.data.SampleData
import com.trakky.app.ui.components.AnimatedAuroraBackground
import com.trakky.app.ui.theme.*

@Composable
fun BookingsScreen() {
    AnimatedAuroraBackground(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize().padding(20.dp).padding(top = 40.dp)) {
            Text("Your Bookings", color = Gold, style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(12.dp))
            SampleData.salons.take(2).forEachIndexed { i, s ->
                Surface(color = Charcoal, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text(s.name, color = Ivory, fontWeight = FontWeight.Bold)
                            Text("Tomorrow · ${listOf("11:30","16:00")[i]}", color = RoseGold)
                            Text("Signature Haircut · ₹${s.priceFrom}", color = Gold)
                        }
                        Surface(color = Gold, shape = RoundedCornerShape(50)) {
                            Text("Confirmed", color = Onyx, fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
                        }
                    }
                }
            }
        }
    }
}
