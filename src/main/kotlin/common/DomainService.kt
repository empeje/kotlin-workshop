package common

interface DomainService {
    val domainRepository: DomainRepository

    fun doSomething(): DomainObject
}

interface DomainRepository {
    fun findDomainObject(): DomainObject
}