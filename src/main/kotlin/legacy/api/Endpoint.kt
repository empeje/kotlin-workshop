package legacy.api

import common.ResponseEntity
import legacy.domain.DomainException
import legacy.domain.DomainExceptionType
import common.DomainObject
import common.DomainObjectApiResponse
import legacy.domain.DomainServiceImpl

class Endpoint(service: DomainServiceImpl) {

    private val service: DomainServiceImpl

    init {
        this.service = service
    }

    fun handle(): ResponseEntity<*> {
        try {
            val result = service.doSomething()
            return map(result)
        } catch (e: Exception) {
            if (e is DomainException) {
                return map(e)
            }
            return ResponseEntity.internalServerError()
        }
    }

    private fun map(result: DomainObject): ResponseEntity<DomainObjectApiResponse> {
        return ResponseEntity.ok(
            DomainObjectApiResponse(
                result.id,
                result.name
            )
        )
    }

    private fun map(e: DomainException): ResponseEntity<*> {
        if (e.type == DomainExceptionType.NOT_FOUND) {
            return ResponseEntity.notFound()
        }
        if (e.type == DomainExceptionType.VALIDATION) {
            return ResponseEntity.badRequest()
        }
        return ResponseEntity.internalServerError()
    }
}