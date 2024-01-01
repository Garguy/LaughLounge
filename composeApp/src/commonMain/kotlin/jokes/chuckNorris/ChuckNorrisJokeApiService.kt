package jokes.chuckNorris

import dev.adamgardner.BuildKonfig
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import utils.HttpClientFactory

class ChuckNorrisJokeApiService {
    private val defaultJoke = "No joke found"
    private val url = "https://api.api-ninjas.com/v1/chucknorris"
    private val client = HttpClientFactory.client

    suspend fun getChuckNorrisJoke(): ChuckNorrisJoke {
        try {
            val response: HttpResponse = client.get(url) {
                headers {
                    header("X-Api-Key", BuildKonfig.API_NINJA_KEY)
                }
            }

            return if (response.status == HttpStatusCode.OK) {
                return response.body<ChuckNorrisJoke>()
            } else {
                ChuckNorrisJoke(defaultJoke)
            }
        } catch (e: Exception) {
            return ChuckNorrisJoke(defaultJoke)
        }
    }
}