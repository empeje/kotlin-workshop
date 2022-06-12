package legacy.testdoubles

import common.DomainObject
import common.DomainRepository
import io.mockk.every
import io.mockk.mockk
import legacy.domain.DomainException
import legacy.domain.DomainExceptionType

class MockDomainRepository : DomainRepository {

    private val mock: DomainRepository = mockk()

    override fun findDomainObject(): DomainObject {
        return mock.findDomainObject()
    }

    fun throwsRuntimeException() {
        every { mock.findDomainObject() }.throws(RuntimeException())
    }

    fun throwsNotFoundDomainException() {
        every { mock.findDomainObject() }.throws(DomainException(DomainExceptionType.NOT_FOUND, "info"))
    }

    fun throwsValidationDomainException() {
        every { mock.findDomainObject() }.throws(DomainException(DomainExceptionType.VALIDATION, "info"))
    }

    fun returns(domainObject: DomainObject) {
        every { mock.findDomainObject() }.returns(domainObject)
    }
}