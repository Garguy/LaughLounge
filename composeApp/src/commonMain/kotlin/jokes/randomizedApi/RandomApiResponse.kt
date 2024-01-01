package jokes.randomizedApi

import jokes.chuckNorris.ChuckNorrisJoke
import jokes.dadJokes.DadJoke
import jokes.devJokes.DevJoke
import jokes.randomJokes.AppSpotRandomJoke
import jokes.randomJokes.RandomJoke

sealed class RandomApiResponse {
    data class RandomJokeResponse(val joke: RandomJoke) : RandomApiResponse()
    data class DevJokeResponse(val devJoke: List<DevJoke>) : RandomApiResponse()
    data class DadJokeResponse(val dadJoke: DadJoke): RandomApiResponse()
    data class ChuckNorrisResponse(val chuckNorrisJoke: ChuckNorrisJoke): RandomApiResponse()
    data class AppSpotRandomJokeResponse(val appSpotRandomJoke: AppSpotRandomJoke): RandomApiResponse()
    data class Error(val message: String) : RandomApiResponse()
}