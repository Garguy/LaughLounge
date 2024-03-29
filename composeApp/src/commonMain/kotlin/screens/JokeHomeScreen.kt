package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jokes.chuckNorris.ChuckNorrisJokeApiService
import jokes.dadJokes.DadJokeApiService
import jokes.devJokes.DevJokeApiService
import jokes.randomJokes.AppSpotRandomJokeApiService
import jokes.randomJokes.RandomJokeApiService
import jokes.randomizedApi.RandomApiResponse
import utils.LaughLoungeTheme

@Composable
fun JokeScreen(
    dadJokeApiService: DadJokeApiService,
    randomJokeApiService: RandomJokeApiService,
    devJokeApiService: DevJokeApiService,
    appSpotRandomJokeApiService: AppSpotRandomJokeApiService,
    chuckNorrisJokeApiService: ChuckNorrisJokeApiService
) {
    var joke by remember { mutableStateOf("Ready for a laugh? Tap the button!") }
    var isLoading by remember { mutableStateOf(false) }
    val apiServices = listOf(
        dadJokeApiService,
        randomJokeApiService,
        devJokeApiService,
        appSpotRandomJokeApiService,
        chuckNorrisJokeApiService
    )

    LaughLoungeTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = joke,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { isLoading = true },
                    enabled = !isLoading,
                    modifier = Modifier.padding(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        Text("Tell me a joke!", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
    }

    if (isLoading) {
        val selectedService = apiServices.random()
        LaunchedEffect(Unit) {
            joke = try {
                when (val result = selectedService.fetchRandomApi()) {
                    is RandomApiResponse.RandomJokeResponse -> result.joke.joke
                    is RandomApiResponse.DevJokeResponse -> (result.devJoke.firstOrNull()?.question + " " + result.devJoke.firstOrNull()?.punchline)
                    is RandomApiResponse.AppSpotRandomJokeResponse -> result.appSpotRandomJoke.setup + " " + result.appSpotRandomJoke.punchline
                    is RandomApiResponse.DadJokeResponse -> result.dadJoke.joke
                    is RandomApiResponse.ChuckNorrisResponse -> result.chuckNorrisJoke.joke
                    is RandomApiResponse.Error -> result.message
                }
            } catch (e: Exception) {
                "${e.message}"
            }
            isLoading = false
        }
    }
}





