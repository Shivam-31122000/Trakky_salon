package com.trakky.app.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.trakky.app.data.Salon
import com.trakky.app.ui.theme.*

@Composable
fun AnimatedAuroraBackground(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    val transition = rememberInfiniteTransition(label = "bg")
    val shift by transition.animateFloat(
        0f, 1f,
        infiniteRepeatable(tween(9000, easing = LinearEasing), RepeatMode.Reverse),
        label = "shift"
    )
    val brush = Brush.linearGradient(
        colors = listOf(Onyx, DeepBurgundy, Charcoal, Onyx),
        start = Offset(0f, 0f + 800f * shift),
        end = Offset(1200f, 1600f - 600f * shift)
    )
    Box(modifier = modifier.background(brush)) { content() }
}

@Composable
fun GoldBorder(modifier: Modifier = Modifier, radius: Int = 24, content: @Composable () -> Unit) {
    Box(
        modifier
            .shadow(20.dp, RoundedCornerShape(radius.dp), ambientColor = Gold, spotColor = Gold)
            .clip(RoundedCornerShape(radius.dp))
            .background(
                Brush.linearGradient(listOf(Charcoal, DeepBurgundy.copy(alpha = 0.7f)))
            )
    ) { content() }
}

@Composable
fun SalonCard(salon: Salon, onClick: () -> Unit) {
    val infinite = rememberInfiniteTransition(label = "card")
    val tilt by infinite.animateFloat(
        -2.5f, 2.5f,
        infiniteRepeatable(tween(3500, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "tilt"
    )
    GoldBorder(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .graphicsLayer {
                rotationY = tilt
                cameraDistance = 16f * density
            }
    ) {
        Box(Modifier.fillMaxSize()) {
            AsyncImage(
                model = salon.image,
                contentDescription = salon.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            0f to Color.Transparent,
                            0.55f to Onyx.copy(alpha = 0.55f),
                            1f to Onyx.copy(alpha = 0.95f)
                        )
                    )
            )
            // Rating chip
            Surface(
                color = Gold,
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
            ) {
                Text(
                    "★ ${salon.rating}",
                    color = Onyx,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
            // Distance chip
            Surface(
                color = Onyx.copy(alpha = 0.6f),
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(12.dp)
            ) {
                Text(
                    "📍 ${salon.distanceKm} km",
                    color = Ivory,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(salon.name, color = Ivory, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(2.dp))
                Text(salon.tagline, color = RoseGold, style = MaterialTheme.typography.labelLarge)
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("${salon.area}, ${salon.city}", color = Ivory.copy(alpha = 0.8f))
                    Spacer(Modifier.weight(1f))
                    Text("from ₹${salon.priceFrom}", color = Gold, fontWeight = FontWeight.Bold)
                }
            }

            Box(
                Modifier
                    .matchParentSize()
                    .background(Color.Transparent)
                    .clip(RoundedCornerShape(24.dp))
            ) {
                // overlay click
                androidx.compose.foundation.layout.Spacer(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Transparent)
                )
            }

            // Whole-card click target
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.Transparent)
            ) {
                androidx.compose.material3.TextButton(
                    onClick = onClick,
                    modifier = Modifier.matchParentSize()
                ) {}
            }
        }
    }
}

@Composable
fun ServicePill(label: String, icon: String, selected: Boolean = false, onClick: () -> Unit = {}) {
    val bg = if (selected) Gold else Charcoal
    val fg = if (selected) Onyx else Ivory
    Surface(
        onClick = onClick,
        color = bg,
        shape = RoundedCornerShape(50),
        shadowElevation = if (selected) 8.dp else 0.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, Gold.copy(alpha = 0.6f))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Text(icon)
            Spacer(Modifier.width(6.dp))
            Text(label, color = fg, fontWeight = FontWeight.SemiBold)
        }
    }
}
