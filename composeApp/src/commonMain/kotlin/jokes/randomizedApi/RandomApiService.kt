package jokes.randomizedApi

interface RandomApiService {
    suspend fun fetchRandomApi(): RandomApiResponse
}