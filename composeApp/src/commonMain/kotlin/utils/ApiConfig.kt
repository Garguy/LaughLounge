package utils

object ApiConfig {
    var ninjaApiKey: String? = null
        private set

    fun setApiKey(key: String) {
        ninjaApiKey = key
    }
}
