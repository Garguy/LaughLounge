package jokes.randomJokes

import kotlinx.serialization.Serializable

@Serializable
data class RandomJoke(
    val joke: String
)
