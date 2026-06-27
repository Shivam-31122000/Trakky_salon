package com.trakky.app.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.trakky.app.data.SampleData
import com.trakky.app.ui.components.*
import com.trakky.app.ui.theme.*

@Composable
fun SalonDetailScreen(salonId: String, onBack: () -> Unit) {
    val salon = SampleData.salons.find { it.id == salonId } ?: return
    var selectedSlot by remember { mutableStateOf<String?>(null) }
    var booked by remember { mutableStateOf(false) }
    val slots = listOf("10:30", "11:15", "12:00", "13:30", "15:00", "16:45", "18:00", "19:30")

    Box(Modifier.fillMaxSize().background(Onyx)) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Box(Modifier.fillMaxWidth().height(320.dp)) {
                AsyncImage(
                    model = salon.image, contentDescription = salon.name,
                    modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
                )
                Box(Modifier.fillMaxSize().background(
                    Brush.verticalGradient(listOf(Onyx.copy(alpha=0.4f), Color.Transparent, Onyx))
                ))
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.padding(16.dp).clip(RoundedCornerShape(50))
                        .background(Onyx.copy(alpha = 0.6f))
                ) { Text("←", color = Gold, fontWeight = FontWeight.Black) }

                Column(Modifier.align(Alignment.BottomStart).padding(20.dp)) {
                    Text(salon.name, color = Ivory, style = MaterialTheme.typography.displayLarge)
                    Text(salon.tagline, color = RoseGold)
                    Spacer(Modifier.height(6.dp))
                    Row {
                        Text("★ ${salon.rating}", color = Gold, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.width(12.dp))
                        Text("📍 ${salon.distanceKm} km · ${salon.area}", color = Ivory)
                    }
                }
            }

            Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text("Services", color = Gold, style = MaterialTheme.typography.titleLarge)
                LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(SampleData.services.filter { svc -> salon.services.any { it.contains(svc.name.split(" ").first(), true) } || salon.services.contains(svc.name) }) { svc ->
                        Surface(color = Charcoal, shape = RoundedCornerShape(16.dp)) {
                            Column(Modifier.padding(14.dp).widthIn(min = 120.dp)) {
                                Text(svc.icon, color = Gold)
                                Spacer(Modifier.height(6.dp))
                                Text(svc.name, color = Ivory, fontWeight = FontWeight.SemiBold)
                                Text("${svc.durationMin} min · ₹${svc.price}", color = RoseGold)
                            }
                        }
                    }
                }

                Text("Pick a slot", color = Gold, style = MaterialTheme.typography.titleLarge)
                FlowRowSlots(slots, selectedSlot) { selectedSlot = it }

                AnimatedVisibility(
                    visible = selectedSlot != null,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    Button(
                        onClick = { booked = true },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Gold, contentColor = Onyx)
                    ) {
                        Text(
                            if (booked) "Booked for $selectedSlot ✓" else "Confirm booking at $selectedSlot",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(Modifier.height(60.dp))
            }
        }
    }
}

@Composable
private fun FlowRowSlots(slots: List<String>, selected: String?, onPick: (String) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        slots.chunked(4).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                row.forEach { s ->
                    val sel = selected == s
                    Surface(
                        onClick = { onPick(s) },
                        shape = RoundedCornerShape(12.dp),
                        color = if (sel) Gold else Charcoal,
                        modifier = Modifier.weight(1f).height(46.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Gold.copy(alpha = 0.5f))
                    ) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(s, color = if (sel) Onyx else Ivory, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
