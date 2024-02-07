
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import components.GlassmorphicBottomNavigation
import dev.chrisbanes.haze.HazeState
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.FirebaseRemoteConfig
import dev.gitlive.firebase.remoteconfig.remoteConfig
import jokes.chuckNorris.ChuckNorrisJokeApiService
import jokes.dadJokes.DadJokeApiService
import jokes.devJokes.DevJokeApiService
import jokes.randomJokes.AppSpotRandomJokeApiService
import jokes.randomJokes.RandomJokeApiService
import screens.JokeScreen
import utils.ApiConfig
import utils.LaughLoungeTheme

@Composable
fun App() {

    LaunchedEffect(Unit) {
        fetchRemoteConfig()
    }

    val hazeState = remember { HazeState() }
    LaughLoungeTheme {
        Scaffold(
            bottomBar = {
                GlassmorphicBottomNavigation(hazeState)
            }
        ) {
            JokeScreen(
                dadJokeApiService = DadJokeApiService(), // Replace with actual instances
                randomJokeApiService = RandomJokeApiService(),
                devJokeApiService = DevJokeApiService(),
                appSpotRandomJokeApiService = AppSpotRandomJokeApiService(),
                chuckNorrisJokeApiService = ChuckNorrisJokeApiService()
            )
        }
    }
}

suspend fun fetchRemoteConfig() {
    val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
    remoteConfig.settings {
        minimumFetchIntervalInSeconds = 3600
    }

    try {
        // Fetch and activate the values from the Remote Config service.
        remoteConfig.fetchAndActivate()
        ApiConfig.setApiKey(remoteConfig.getValue("ninja_api_key").asString())
    } catch (e: Exception) {
        // TODO add crashlytics and log that we are not getting the API key.
        println("Error, need to update this error")
    }
}