package com.trakky.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.trakky.app.ui.components.AnimatedAuroraBackground
import com.trakky.app.ui.theme.*

@Composable
fun ProfileScreen() {
    AnimatedAuroraBackground(Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize().padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier.size(110.dp).clip(RoundedCornerShape(50))
                    .background(Brush.radialGradient(listOf(Gold, DeepBurgundy))),
                contentAlignment = Alignment.Center
            ) { Text("AY", color = Onyx, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Black) }
            Spacer(Modifier.height(12.dp))
            Text("Aarav Yadav", color = Ivory, style = MaterialTheme.typography.titleLarge)
            Text("Member since 2024 · Gold tier", color = Gold)
            Spacer(Modifier.height(24.dp))

            Row(Modifier.fillMaxWidth().padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf("Visits" to "24", "Saved" to "₹4,820", "Salons" to "11").forEach { (l, v) ->
                    Surface(color = Charcoal, shape = RoundedCornerShape(20.dp), modifier = Modifier.weight(1f)) {
                        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(v, color = Gold, fontWeight = FontWeight.Black, style = MaterialTheme.typography.titleLarge)
                            Text(l, color = RoseGold)
                        }
                    }
                }
            }
            Spacer(Modifier.height(24.dp))
            listOf("My favorites","Payment methods","Notifications","Help & support","Sign out").forEach { item ->
                Surface(color = Charcoal, shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 4.dp)) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(item, color = Ivory, modifier = Modifier.weight(1f))
                        Text("›", color = Gold)
                    }
                }
            }
        }
    }
}
