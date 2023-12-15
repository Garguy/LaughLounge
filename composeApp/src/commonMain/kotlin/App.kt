
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dadJokes.JokeApiService
import dadJokes.JokeScreen
import dev.chrisbanes.haze.HazeState

@Composable
fun App() {
    val hazeState = remember { HazeState() }

    LaughLoungeTheme {
        Scaffold(
            bottomBar = {
                GlassmorphicBottomNavigation(hazeState)
            }
        ) {
            val jokeApiService = JokeApiService()
            JokeScreen(jokeApiService = jokeApiService)
        }
    }
}