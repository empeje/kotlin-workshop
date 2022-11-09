package refactored.domain

sealed class DomainException(type: String, info: String) : RuntimeException("[$type]: $info") {
    data class NotFound(val info: String) : DomainException("NotFound", info)
    data class Validation(val info: String) : DomainException("Validation",info)
}


