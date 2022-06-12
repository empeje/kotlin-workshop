package refactored.api

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import common.DomainObject
import common.DomainObjectApiResponse
import org.junit.jupiter.api.Test
import refactored.testdoubles.MockDomainRepository

//private typealias NotFoundDomainException = DomainException.NotFound
//private typealias ValidationDomainException = DomainException.Validation

class EndpointTest {

//
//    private val repository = MockDomainRepository()
//    private val endpoint = TODO()
//
//    @Test
//    fun `should return response entity with domain object`() {
//        // given
//        repository returns DomainObject(1L, "name")
//
//        // when
//        val response = endpoint.handle()
//
//        // then
//        assertThat(response).apply {
//            transform { it.statusCode }.isEqualTo(200)
//            transform { it.body }.isEqualTo(DomainObjectApiResponse(1L, "name"))
//        }
//    }
//
//    @Test
//    fun `should return response entity with 404 status code`() {
//        // given
//        repository throws NotFoundDomainException("info")
//
//        // when
//        val response = endpoint.handle()
//
//        // then
//        assertThat(response).apply {
//            transform { it.statusCode }.isEqualTo(404)
//            transform { it.body }.isNull()
//        }
//    }
//
//    @Test
//    fun `should return response entity with 400 status code`() {
//        // given
//        repository throws ValidationDomainException("info")
//
//        // when
//        val response = endpoint.handle()
//
//        // then
//        assertThat(response).apply {
//            transform { it.statusCode }.isEqualTo(400)
//            transform { it.body }.isNull()
//        }
//    }
//
//    @Test
//    fun `should return response entity with 500 status code`() {
//        // given
//        repository throws RuntimeException()
//
//        // when
//        val response = endpoint.handle()
//
//        // then
//        assertThat(response).apply {
//            transform { it.statusCode }.isEqualTo(500)
//            transform { it.body }.isNull()
//        }
//    }
}