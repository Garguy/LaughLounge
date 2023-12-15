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
fun LaughLoungeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) darkColorPalette else lightColorPalette

    MaterialTheme(
        colorScheme = colors,
        shapes = Shapes(small = RoundedCornerShape(8.dp)),
        content = content
    )
}

// Light theme colors
private val lightColorPalette = lightColorScheme(
    primary = Color(0xFF7393B3), // Muted Blue
    secondary = Color(0xFF0077B6), // Cool Blue
    onPrimary = Color.White, // White on Cool Blue
    primaryContainer = Color(0xFF023E8A), // Deep Blue
    onSecondaryContainer = Color.White,
    onPrimaryContainer = Color.White,
    onSecondary = Color.Black, // Black on Light Blue
    surface = Color.White, // White surface
    onSurface = Color.Black, // Black on White surface
    background = Color.White, // White background
    onBackground = Color.Black // Black on White background
)

// Dark theme colors
private val darkColorPalette = darkColorScheme(
    secondary = Color(0xFF005f99), // Darker Cool Blue
    onPrimary = Color.White, // White on Dark Cool Blue
    primaryContainer = Color(0xFF023E8A), // Deep Blue
    onSecondaryContainer = Color.White,
    onPrimaryContainer = Color.White, // White on Dark Cool Blue
    primary = Color(0xFF7393B3), // Muted Blue
    onSecondary = Color.White, // White on Muted Blue
    surface = Color(0xFF121212), // Dark Surface
    onSurface = Color.White, // White on Dark Surface
    background = Color(0xFF121212), // Dark Background
    onBackground = Color.White // White on Dark Background
)

