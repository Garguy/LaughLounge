
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.chrisbanes.haze.HazeState
import jokes.JokeScreen
import jokes.dadJokes.DadJokeApiService
import jokes.randomJokes.RandomJokeApiService

@Composable
fun App() {
    val hazeState = remember { HazeState() }

    LaughLoungeTheme {
        Scaffold(
            bottomBar = {
                GlassmorphicBottomNavigation(hazeState)
            }
        ) {
            val jokeApiService = DadJokeApiService()
            val randomJokeApiService = RandomJokeApiService()
            JokeScreen(dadJokeApiService = jokeApiService, randomJokeApiService = randomJokeApiService)
        }
    }
}