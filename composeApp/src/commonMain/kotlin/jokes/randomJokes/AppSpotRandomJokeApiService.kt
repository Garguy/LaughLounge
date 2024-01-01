package jokes.randomJokes

import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import jokes.randomizedApi.RandomApiResponse
import jokes.randomizedApi.RandomApiService
import kotlinx.serialization.json.Json
import utils.HttpClientFactory

class AppSpotRandomJokeApiService: RandomApiService {
    private val client = HttpClientFactory.client
    private val defaultJoke =
        AppSpotRandomJoke(id = 0, type = "N/A", setup = "Error", punchline = "No joke available")

    suspend fun getAppSpotRandomJoke(): AppSpotRandomJoke {
        val url = "https://official-joke-api.appspot.com/random_joke"

        return try {
            val response: HttpResponse = client.get(url)

            if (response.status == HttpStatusCode.OK) {
                val jsonString = response.bodyAsText()
                Json.decodeFromString<AppSpotRandomJoke>(jsonString)
            } else {
                defaultJoke
            }
        } catch (e: Exception) {
            defaultJoke
        }
    }

    override suspend fun fetchRandomApi(): RandomApiResponse {
        return RandomApiResponse.AppSpotRandomJokeResponse(getAppSpotRandomJoke())
    }
}