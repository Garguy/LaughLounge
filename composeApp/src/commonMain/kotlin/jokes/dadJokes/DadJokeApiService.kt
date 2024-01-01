package jokes.dadJokes

import io.ktor.client.call.body
import io.ktor.client.request.get
import utils.HttpClientFactory

class DadJokeApiService {
    private val client = HttpClientFactory.client

    suspend fun getDadJoke(): DadJoke {
        return client.get("https://icanhazdadjoke.com/").body()
    }
}