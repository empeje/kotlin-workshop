package refactored.api

import common.DomainObject
import common.DomainObjectApiResponse
import common.DomainService
import common.ResponseEntity
import legacy.domain.DomainExceptionType
import refactored.domain.DomainException

class Endpoint(
    private val domainService: DomainService
    ) {

    fun handle(): ResponseEntity<*> {
        try {
            val result = domainService.doSomething()
            return result.map()
        } catch (e: Exception) {
            if (e is DomainException) {
                return e.map()
            }
            return ResponseEntity.internalServerError()
        }
    }



    private fun DomainObject.map(): ResponseEntity<DomainObjectApiResponse> {
        return ResponseEntity.ok(
            DomainObjectApiResponse(
                this.id,
                this.name
            )
        )
    }

    private fun DomainException.map(): ResponseEntity<*> = when(this) {
            is DomainException.NotFound -> ResponseEntity.notFound()
            is DomainException.Validation -> ResponseEntity.badRequest()
        }
}
