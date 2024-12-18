import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFE57373),
    secondary = Color(0xFF90CAF9),
    tertiary = Color(0xFF81C784),
    onBackground = Color.White,
    onSurface = Color.White,
    surface = Color.Black,
    background = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFD32F2F),
    secondary = Color(0xFF1976D2),
    tertiary = Color(0xFF388E3C),
    onBackground = Color.Black,
    onSurface = Color.Black,
    surface = Color.White,
    background = Color.White
)

@Composable
fun FuelCalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
} 