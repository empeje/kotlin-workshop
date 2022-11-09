package refactored

interface Logger {
    fun logInfo(message: String)
    class Sout : Logger {
        override fun logInfo(message: String) {
            println("[INFO]: $message")
        }
    }
}