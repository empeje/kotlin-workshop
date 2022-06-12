package legacy.api

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import common.DomainObject
import common.DomainObjectApiResponse
import legacy.testdoubles.MockDomainRepository
import legacy.domain.DomainServiceImpl
import org.junit.jupiter.api.Test

class EndpointTest {

    private val repository = MockDomainRepository()
    private val endpoint = Endpoint(DomainServiceImpl(repository))

    @Test
    fun shouldReturnResponseEntityWithDomainObject() {
        // given
        repository.returns(DomainObject(1L, "name"))

        // when
        val response = endpoint.handle()

        // then
        assertThat(response).apply {
            transform { it.statusCode }.isEqualTo(200)
            transform { it.body }.isEqualTo(DomainObjectApiResponse(1L, "name"))
        }
    }

    @Test
    fun shouldReturnResponseEntityWith404StatusCode() {
        // given
        repository.throwsNotFoundDomainException()

        // when
        val response = endpoint.handle()

        // then
        assertThat(response).apply {
            transform { it.statusCode }.isEqualTo(404)
            transform { it.body }.isNull()
        }
    }

    @Test
    fun shouldReturnResponseEntityWith400StatusCode() {
        // given
        repository.throwsValidationDomainException()

        // when
        val response = endpoint.handle()

        // then
        assertThat(response).apply {
            transform { it.statusCode }.isEqualTo(400)
            transform { it.body }.isNull()
        }
    }

    @Test
    fun shouldReturnResponseWithEntity500StatusCode() {
        // given
        repository.throwsRuntimeException()

        // when
        val response = endpoint.handle()

        // then
        assertThat(response).apply {
            transform { it.statusCode }.isEqualTo(500)
            transform { it.body }.isNull()
        }
    }
}