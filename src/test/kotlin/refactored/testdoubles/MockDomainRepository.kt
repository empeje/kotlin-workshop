package refactored.testdoubles

import common.DomainObject
import common.DomainRepository
import io.mockk.every
import io.mockk.mockk

class MockDomainRepository(private val mock: DomainRepository = mockk()) : DomainRepository by mock {

    infix fun throws (e: Exception) {
        every { mock.findDomainObject() } throws e
    }

    infix fun returns(domainObject: DomainObject) {
        every { mock.findDomainObject() } returns domainObject
    }
}