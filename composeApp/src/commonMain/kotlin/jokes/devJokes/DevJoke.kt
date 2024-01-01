package jokes.devJokes

import kotlinx.serialization.Serializable

@Serializable
data class DevJoke(val question: String, val punchline: String)