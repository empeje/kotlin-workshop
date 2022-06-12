package refactored.api

import common.DomainService
import common.ResponseEntity

class Endpoint(
    private val domainService: DomainService
) {

    fun handle(): ResponseEntity<*> =
        TODO()
}