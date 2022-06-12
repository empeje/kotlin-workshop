package refactored.api

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import common.DomainObject
import common.DomainObjectApiResponse
import org.junit.jupiter.api.Test
import refactored.domain.DomainServiceImpl
import refactored.testdoubles.MockDomainRepository

class EndpointTest {

    private val repository = MockDomainRepository()
    private val endpoint = Endpoint(DomainServiceImpl(repository))

    @Test
    fun `should return response entity with domain object`() {
        // given
        repository returns DomainObject(1L, "name")

        // when
        val response = endpoint.handle()

        // then
        assertThat(response).apply {
            transform { it.statusCode }.isEqualTo(200)
            transform { it.body }.isEqualTo(DomainObjectApiResponse(1L, "name"))
        }
    }

    @Test
    fun `should return response entity with 404 status code`() {
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
    fun `should return response entity with 400 status code`() {
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
    fun `should return response entity with 500 status code`() {
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