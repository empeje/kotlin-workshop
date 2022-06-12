package refactored.testdoubles

import common.DomainObject
import common.DomainRepository
import io.mockk.every
import io.mockk.mockk
import refactored.domain.DomainException

class MockDomainRepository(private val mock: DomainRepository = mockk()) : DomainRepository by mock {

    fun throwsRuntimeException() {
        every { mock.findDomainObject() } throws RuntimeException()
    }

    fun throwsNotFoundDomainException() {
        every { mock.findDomainObject() } throws DomainException.NotFound("info")
    }

    fun throwsValidationDomainException() {
        every { mock.findDomainObject() } throws DomainException.Validation("info")
    }

    infix fun returns(domainObject: DomainObject) {
        every { mock.findDomainObject() } returns domainObject
    }
}