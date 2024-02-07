package jokes.chuckNorris

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import jokes.randomizedApi.RandomApiResponse
import jokes.randomizedApi.RandomApiService
import utils.ApiConfig
import utils.HttpClientFactory

class ChuckNorrisJokeApiService: RandomApiService {
    private val defaultJoke = "No joke found"
    private val url = "https://api.api-ninjas.com/v1/chucknorris"
    private val client = HttpClientFactory.client

    suspend fun getChuckNorrisJoke(): ChuckNorrisJoke {
        try {
            val response: HttpResponse = client.get(url) {
                headers {
                    header("X-Api-Key", ApiConfig.ninjaApiKey)
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

    override suspend fun fetchRandomApi(): RandomApiResponse {
        return RandomApiResponse.ChuckNorrisResponse(getChuckNorrisJoke())
    }
}