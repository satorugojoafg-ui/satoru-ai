package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val GojoColorScheme = darkColorScheme(
    primary = NeonPurple,
    onPrimary = Color.White,
    primaryContainer = Color(0xFF352F60),
    onPrimaryContainer = Color(0xFFEADBFF),
    secondary = NeonCyan,
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFF004F58),
    onSecondaryContainer = Color(0xFFA6EEF9),
    tertiary = NeonBlue,
    onTertiary = Color.White,
    background = CosmicBackground,
    onBackground = TextPrimary,
    surface = CosmicCardSurface,
    onSurface = TextPrimary,
    surfaceVariant = CosmicCardSurfaceElevated,
    onSurfaceVariant = TextSecondary,
    outline = CosmicCardBorder,
    outlineVariant = Color(0x1FFFFFFF)
)

@Composable
fun GojoAITheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = GojoColorScheme,
        typography = Typography,
        content = content
    )
}
