package legacy.domain

import common.DomainObject
import common.DomainService
import common.DomainRepository

class DomainServiceImpl(domainRepository: DomainRepository) : DomainService {

    override val domainRepository: DomainRepository

    init {
        this.domainRepository = domainRepository
    }

    override fun doSomething(): DomainObject {
        return domainRepository.findDomainObject()
    }
}