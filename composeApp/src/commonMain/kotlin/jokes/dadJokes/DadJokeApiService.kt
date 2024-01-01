package jokes.dadJokes

import io.ktor.client.call.body
import io.ktor.client.request.get
import jokes.randomizedApi.RandomApiResponse
import jokes.randomizedApi.RandomApiService
import utils.HttpClientFactory

class DadJokeApiService: RandomApiService {
    private val client = HttpClientFactory.client

    suspend fun getDadJoke(): DadJoke {
        return client.get("https://icanhazdadjoke.com/").body()
    }

    override suspend fun fetchRandomApi(): RandomApiResponse {
        return RandomApiResponse.DadJokeResponse(getDadJoke())
    }
}