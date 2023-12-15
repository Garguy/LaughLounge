package dadJokes

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class JokeApiService {

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

        defaultRequest {
            headers {
                append(HttpHeaders.Accept, "application/json")
            }
        }
    }

    suspend fun getRandomJoke(): DadJoke {
        return client.get("https://icanhazdadjoke.com/").body()
    }
}