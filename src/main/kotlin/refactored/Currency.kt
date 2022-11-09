package refactored


data class Currency(val value: String) {
    companion object {
        fun of(value: String) : Currency {
            return Currency(value)
        }

        val EUR = Currency("EUR");
        val PLN = Currency("PLN");
    }
}
