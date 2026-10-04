package com.nexusai.studio.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val NexusDarkColorScheme = darkColorScheme(
    primary = Lime,
    onPrimary = DeepLagoon,
    primaryContainer = LimeDim,
    onPrimaryContainer = Lime,
    secondary = Sky,
    onSecondary = DeepLagoon,
    secondaryContainer = SkySubtle,
    onSecondaryContainer = Sky,
    tertiary = Coral,
    onTertiary = Color.White,
    tertiaryContainer = CoralSubtle,
    onTertiaryContainer = Coral,
    background = DeepLagoon,
    onBackground = MistPrimary,
    surface = Surface,
    onSurface = MistPrimary,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = MistSecondary,
    error = Coral,
    onError = Color.White,
    outline = MistTertiary,
    outlineVariant = MistDisabled,
    inverseSurface = MistPrimary,
    inverseOnSurface = DeepLagoon,
)

@Composable
fun NexusAITheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = NexusDarkColorScheme,
        typography = NexusTypography,
        content = content
    )
}
