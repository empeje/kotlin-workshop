package refactored.domain

import common.DomainObject
import common.DomainService
import common.DomainRepository

class DomainServiceImpl(override val domainRepository: DomainRepository) : DomainService {
    override fun doSomething(): DomainObject = domainRepository.findDomainObject()
}