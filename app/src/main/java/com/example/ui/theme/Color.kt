package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val CosmicBackground = Color(0xFF171B24)
val CosmicBackgroundSecondary = Color(0xFF1E232D)
val CosmicCardSurface = Color(0xFF232936)
val CosmicCardSurfaceElevated = Color(0xFF2B3240)
val CosmicCardBorder = Color(0x2EFFFFFF)

val NeonPurple = Color(0xFF8C7CFF)
val NeonCyan = Color(0xFF4DD0E1)
val NeonBlue = Color(0xFF5D75FA)
val NeonPink = Color(0xFFFF70A6)
val WarmGold = Color(0xFFFFB703)
val SuccessGreen = Color(0xFF06D6A0)

val TextPrimary = Color(0xFFF4F5F8)
val TextSecondary = Color(0xFFC4C9D3)
val TextMuted = Color(0xFF8E95A5)

val CosmicGlowGradient = Brush.horizontalGradient(
    colors = listOf(NeonBlue, NeonPurple, NeonCyan)
)

val CosmicCardGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0x338C7CFF),
        Color(0x102B3240)
    )
)

val HeaderGradient = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFF5D75FA),
        Color(0xFF8C7CFF)
    )
)
