package jokes.devJokes

import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import jokes.randomizedApi.RandomApiResponse
import jokes.randomizedApi.RandomApiService
import kotlinx.serialization.json.Json
import utils.HttpClientFactory

class DevJokeApiService : RandomApiService {
    private val url = "https://backend-omega-seven.vercel.app/api/getjoke"
    private val defaultJoke: DevJoke =
        DevJoke(question = "Error:", punchline = "Unable to retrieve joke")
    private val client = HttpClientFactory.client

    suspend fun getDevJoke(): List<DevJoke>? {
        return try {
            val response: HttpResponse = client.get(url)

            if (response.status == HttpStatusCode.OK) {
                val responseBody = response.bodyAsText()
                Json.decodeFromString<List<DevJoke>>(responseBody)
            } else {
                listOf(defaultJoke)
            }
        } catch (e: Exception) {
            listOf(defaultJoke)
        }
    }

    override suspend fun fetchRandomApi(): RandomApiResponse {
        return RandomApiResponse.DevJokeResponse(getDevJoke() ?: listOf())
    }
}
