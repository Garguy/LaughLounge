package jokes.randomJokes

import dev.adamgardner.BuildKonfig.API_NINJA_KEY
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import jokes.randomizedApi.RandomApiResponse
import jokes.randomizedApi.RandomApiService
import utils.HttpClientFactory

class RandomJokeApiService : RandomApiService {
    private val defaultJoke = "No joke found"
    private val url = "https://api.api-ninjas.com/v1/jokes?limit=1"
    private val client = HttpClientFactory.client

    suspend fun getRandomJoke(): RandomJoke {
        try {
            val response: HttpResponse = client.get(url) {
                headers {
                    header("X-Api-Key", API_NINJA_KEY)
                }
            }

            return if (response.status == HttpStatusCode.OK) {
                val jokes: List<RandomJoke> = response.body<List<RandomJoke>>()
                jokes.firstOrNull() ?: RandomJoke(defaultJoke)
            } else {
                RandomJoke(defaultJoke)
            }
        } catch (e: Exception) {
            return RandomJoke(defaultJoke)
        }
    }

    override suspend fun fetchRandomApi(): RandomApiResponse {
        return RandomApiResponse.RandomJokeResponse(getRandomJoke())
    }
}