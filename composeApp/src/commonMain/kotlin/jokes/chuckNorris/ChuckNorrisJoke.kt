package jokes.chuckNorris

import kotlinx.serialization.Serializable

@Serializable
data class ChuckNorrisJoke(
    val joke: String
)