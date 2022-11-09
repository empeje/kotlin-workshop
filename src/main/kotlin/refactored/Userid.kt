package refactored

data class UserId(val value: Long) {
    companion object {
        fun of(value: Long): UserId {
            return UserId(value)
        }
    }
}