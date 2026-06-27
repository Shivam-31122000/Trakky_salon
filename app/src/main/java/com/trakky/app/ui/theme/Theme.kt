package com.trakky.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Gold = Color(0xFFD4AF37)
val RoseGold = Color(0xFFE8B4A0)
val DeepBurgundy = Color(0xFF2A0E13)
val Onyx = Color(0xFF0B0608)
val Charcoal = Color(0xFF14090C)
val Ivory = Color(0xFFF5EFE6)
val Copper = Color(0xFFB87333)
val MintGlow = Color(0xFF7FE3C4)

private val Scheme = darkColorScheme(
    primary = Gold,
    onPrimary = Onyx,
    secondary = RoseGold,
    onSecondary = Onyx,
    tertiary = Copper,
    background = Onyx,
    onBackground = Ivory,
    surface = Charcoal,
    onSurface = Ivory,
    surfaceVariant = DeepBurgundy,
    onSurfaceVariant = Ivory,
)

private val AppTypography = Typography(
    displayLarge = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Black, letterSpacing = (-1).sp),
    headlineMedium = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, letterSpacing = (-0.5).sp),
    titleLarge = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
    bodyLarge = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal, lineHeight = 22.sp),
    labelLarge = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Medium, letterSpacing = 0.8.sp),
)

@Composable
fun TrakkyTheme(content: @Composable () -> Unit) {
    @Suppress("UNUSED_VARIABLE") val dark = isSystemInDarkTheme()
    MaterialTheme(colorScheme = Scheme, typography = AppTypography, content = content)
}
