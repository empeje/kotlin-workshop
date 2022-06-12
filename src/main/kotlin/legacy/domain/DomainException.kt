package legacy.domain

class DomainException(val type: DomainExceptionType, val info: String) : RuntimeException("[$type]: $info") {

    override fun fillInStackTrace() = this
}

enum class DomainExceptionType {
    VALIDATION, NOT_FOUND
}