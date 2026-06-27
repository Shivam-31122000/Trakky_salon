package com.trakky.app.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.trakky.app.data.SampleData
import com.trakky.app.ui.components.*
import com.trakky.app.ui.theme.*

@Composable
fun NearbyScreen(onOpenSalon: (String) -> Unit) {
    val salons = SampleData.salons.sortedBy { it.distanceKm }

    AnimatedAuroraBackground(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize().padding(top = 56.dp)) {
            Text(
                "Salons near you 📍",
                color = Gold,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "Sorted by distance — book in minutes",
                color = RoseGold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(Modifier.height(16.dp))

            // Faux radar map preview
            GoldBorder(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(200.dp)
            ) {
                RadarMap(salons.map { it.distanceKm.toFloat() to it.name })
            }

            Spacer(Modifier.height(16.dp))
            LazyColumn(
                contentPadding = PaddingValues(16.dp, bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(salons) { s ->
                    Surface(
                        onClick = { onOpenSalon(s.id) },
                        shape = RoundedCornerShape(20.dp),
                        color = Charcoal
                    ) {
                        Row(
                            Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                Modifier.size(54.dp).clip(RoundedCornerShape(50))
                                    .background(
                                        Brush.radialGradient(listOf(Gold, DeepBurgundy))
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("${s.distanceKm}", color = Onyx, fontWeight = FontWeight.Black)
                            }
                            Spacer(Modifier.width(14.dp))
                            Column(Modifier.weight(1f)) {
                                Text(s.name, color = Ivory, fontWeight = FontWeight.Bold)
                                Text("${s.area}, ${s.city}", color = RoseGold)
                                Text("★ ${s.rating}  ·  from ₹${s.priceFrom}", color = Gold)
                            }
                            Text("›", color = Gold, style = MaterialTheme.typography.headlineMedium)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RadarMap(points: List<Pair<Float, String>>) {
    val infinite = rememberInfiniteTransition(label = "radar")
    val sweep by infinite.animateFloat(
        0f, 360f, infiniteRepeatable(tween(3000, easing = LinearEasing)), label = "sweep"
    )
    Canvas(Modifier.fillMaxSize()) {
        val cx = size.width / 2
        val cy = size.height / 2
        val r = size.minDimension / 2 - 12f

        // Rings
        for (i in 1..4) {
            drawCircle(
                color = Gold.copy(alpha = 0.18f),
                radius = r * i / 4f,
                center = Offset(cx, cy),
                style = Stroke(width = 1.5f)
            )
        }
        // Cross axes
        drawLine(Gold.copy(alpha = 0.15f), Offset(cx - r, cy), Offset(cx + r, cy), 1f)
        drawLine(Gold.copy(alpha = 0.15f), Offset(cx, cy - r), Offset(cx, cy + r), 1f)

        // Sweep beam
        drawArc(
            brush = Brush.sweepGradient(
                0f to Color.Transparent,
                0.1f to Gold.copy(alpha = 0.45f),
                0.2f to Color.Transparent,
                1f to Color.Transparent,
                center = Offset(cx, cy)
            ),
            startAngle = sweep,
            sweepAngle = 80f,
            useCenter = true,
            topLeft = Offset(cx - r, cy - r),
            size = androidx.compose.ui.geometry.Size(r * 2, r * 2)
        )

        // Salon dots
        points.forEachIndexed { i, (d, _) ->
            val angle = Math.toRadians((i * 47).toDouble())
            val dist = (d / 6f).coerceIn(0.15f, 0.95f) * r
            val x = cx + (kotlin.math.cos(angle) * dist).toFloat()
            val y = cy + (kotlin.math.sin(angle) * dist).toFloat()
            drawCircle(RoseGold, radius = 6f, center = Offset(x, y))
            drawCircle(Gold.copy(alpha = 0.5f), radius = 12f, center = Offset(x, y), style = Stroke(width = 2f))
        }
        // Center "you"
        drawCircle(Gold, radius = 8f, center = Offset(cx, cy))
        drawCircle(Color.White, radius = 3f, center = Offset(cx, cy))
    }
}
