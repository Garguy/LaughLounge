package jokes.randomJokes

import dev.adamgardner.BuildKonfig.API_NINJA_KEY
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RandomJokeApiService {
    private val defaultJoke = "No joke found"
    private val url = "https://api.api-ninjas.com/v1/jokes?limit=1"


    private val client = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun getRandomJoke(): RandomJoke {
        try {
            val response: HttpResponse = client.get(url) {
                headers {
                    header(HttpHeaders.Accept, "application/json")
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
}