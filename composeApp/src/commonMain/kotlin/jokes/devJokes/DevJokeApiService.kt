package jokes.devJokes

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class DevJokeApiService {
    private val url = "https://backend-omega-seven.vercel.app/api/getjoke"
    private val defaultJoke: DevJoke =
        DevJoke(question = "Error:", punchline = "Unable to retrieve joke")

    private val client = HttpClient {
        install(Logging) {
            level = LogLevel.INFO
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun getDevJoke(): List<DevJoke>? {
        return try {
            val response: HttpResponse = client.get(url)
            val responseBody = response.bodyAsText()
            Json.decodeFromString<List<DevJoke>>(responseBody)
        } catch (e: Exception) {
            null
        }
    }
}
