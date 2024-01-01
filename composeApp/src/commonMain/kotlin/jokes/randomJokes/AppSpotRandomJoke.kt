package jokes.randomJokes

import kotlinx.serialization.Serializable

@Serializable
data class AppSpotRandomJoke(
    val id: Int,
    val type: String,
    val setup: String,
    val punchline: String
)