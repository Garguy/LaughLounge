package dadJokes

import kotlinx.serialization.Serializable

@Serializable
data class DadJoke(val id: String, val joke: String, val status: Int)
