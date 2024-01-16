package utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Custom theme with a cool blue and white color scheme
@Composable
fun LaughLoungeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) darkColorPalette else lightColorPalette

    MaterialTheme(
        colorScheme = colors,
        shapes = Shapes(small = RoundedCornerShape(8.dp)),
        content = content
    )
}

// Light theme colors
private val lightColorPalette = lightColorScheme(
    primary = Color(0xFF1CC6C4), // Cyan-like shade
    secondary = Color(0xFF8ED9CA), // Soft teal
    onPrimary = Color(0xFF072E5B), // Dark blue for text/icons on primary color
    primaryContainer = Color(0xFFF8D3C9), // Light coral for containers of primary color
    onSecondaryContainer = Color.White, // White text/icons on secondary containers
    onPrimaryContainer = Color.White, // White text/icons on primary containers
    onSecondary = Color(0xFF072E5B), // Dark blue for text/icons on secondary color
    surface = Color.White, // White surface
    onSurface = Color.Black, // Black text/icons on surface
    background = Color.White, // White background
    onBackground = Color.Black // Black text/icons on background
)

// Dark theme colors
private val darkColorPalette = darkColorScheme(
    primary = Color(0xFF094241), // Dark cyan-like shade
    secondary = Color(0xFF1E594D), // Dark green
    onPrimary = Color(0xFFBB9624), // This was the 'surface' color, but now used for contrast on primary
    primaryContainer = Color(0xFF020F1E), // Very dark blue for containers of primary color
    onSecondaryContainer = Color.White, // White text/icons on secondary containers
    onPrimaryContainer = Color.White, // White text/icons on primary containers
    onSecondary = Color(0xFFBB9624), // This was the 'surface' color, but now used for contrast on secondary
    surface = Color(0xFF020F1E), // Very dark blue for surface
    onSurface = Color.White, // White text/icons on surface
    background = Color(0xFF020F1E), // Very dark blue for background
    onBackground = Color.White // White text/icons on background
)

