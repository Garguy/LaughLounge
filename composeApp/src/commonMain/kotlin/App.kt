import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.chrisbanes.haze.HazeState
import jokes.JokeScreen
import jokes.chuckNorris.ChuckNorrisJokeApiService
import jokes.dadJokes.DadJokeApiService
import jokes.devJokes.DevJokeApiService
import jokes.randomJokes.AppSpotRandomJokeApiService
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
            val devJokeApiService = DevJokeApiService()
            val appSpotRandomJoke = AppSpotRandomJokeApiService()
            val chuckNorrisJoke = ChuckNorrisJokeApiService()
            JokeScreen(
                dadJokeApiService = jokeApiService,
                randomJokeApiService = randomJokeApiService,
                devJokeApiService = devJokeApiService,
                appSpotRandomJoke = appSpotRandomJoke,
                chuckNorrisJoke = chuckNorrisJoke
            )
        }
    }
}