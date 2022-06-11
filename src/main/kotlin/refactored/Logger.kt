package refactored

interface Logger {

    fun logInfo(message: String)

    object Sout : Logger {

        override fun logInfo(message: String) {
            println("[INFO]: $message")
        }
    }
}