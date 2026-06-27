package com.trakky.app.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp
import com.trakky.app.ui.theme.Gold
import com.trakky.app.ui.theme.RoseGold
import kotlin.math.cos
import kotlin.math.sin

/** Animated barber scissor loader — blades open and close while a hair strand falls. */
@Composable
fun ScissorLoader(modifier: Modifier = Modifier, size: Int = 120) {
    val infinite = rememberInfiniteTransition(label = "scissor")
    val open by infinite.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(700, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "open"
    )
    val rotate by infinite.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(4000, easing = LinearEasing)),
        label = "rotate"
    )
    val hair by infinite.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1400, easing = LinearEasing)),
        label = "hair"
    )

    Box(modifier = modifier.size(size.dp), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val cx = this.size.width / 2
            val cy = this.size.height / 2
            val radius = this.size.minDimension / 2

            // Glow ring
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Gold.copy(alpha = 0.25f), Color.Transparent),
                    center = Offset(cx, cy),
                    radius = radius
                ),
                radius = radius
            )

            // Falling hair strand
            val hairY = -radius + hair * radius * 2
            drawLine(
                color = RoseGold.copy(alpha = 1f - hair),
                start = Offset(cx + radius * 0.35f, cy + hairY),
                end = Offset(cx + radius * 0.35f, cy + hairY + 24f),
                strokeWidth = 3f,
                cap = StrokeCap.Round
            )

            // Scissor rotating
            rotate(rotate, pivot = Offset(cx, cy)) {
                val bladeLen = radius * 0.85f
                val angleDeg = 18f + open * 30f
                val ang = Math.toRadians(angleDeg.toDouble())
                val dx = (cos(ang) * bladeLen).toFloat()
                val dy = (sin(ang) * bladeLen).toFloat()
                val pivot = Offset(cx, cy)

                // Two blades (mirrored)
                val bladeStroke = Stroke(width = 8f, cap = StrokeCap.Round)
                drawLine(Gold, pivot, Offset(cx + dx, cy - dy), 8f, StrokeCap.Round)
                drawLine(Gold, pivot, Offset(cx + dx, cy + dy), 8f, StrokeCap.Round)

                // Highlights
                drawLine(Color.White.copy(alpha = 0.6f), pivot, Offset(cx + dx * 0.45f, cy - dy * 0.45f), 2f, StrokeCap.Round)
                drawLine(Color.White.copy(alpha = 0.6f), pivot, Offset(cx + dx * 0.45f, cy + dy * 0.45f), 2f, StrokeCap.Round)

                // Handles (rings)
                val handleR = radius * 0.18f
                val hx = cx - bladeLen * 0.55f
                drawCircle(Gold, radius = handleR, center = Offset(hx, cy - handleR * 0.9f), style = Stroke(width = 6f))
                drawCircle(RoseGold, radius = handleR, center = Offset(hx, cy + handleR * 0.9f), style = Stroke(width = 6f))

                // Pivot screw
                drawCircle(Color.White, radius = 5f, center = pivot)
                drawCircle(Gold, radius = 3f, center = pivot)
                @Suppress("UNUSED_VARIABLE") val unused = bladeStroke
            }
        }
    }
}

@Composable
fun FullScreenScissorLoader(label: String = "Styling your experience…") {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ScissorLoader(size = 140)
        Spacer(Modifier.height(20.dp))
        androidx.compose.material3.Text(
            text = label,
            color = Gold,
            style = androidx.compose.material3.MaterialTheme.typography.labelLarge
        )
    }
}
