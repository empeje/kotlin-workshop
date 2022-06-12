package common

class ResponseEntity<T> private constructor(val statusCode: Int, val body: T? = null) {

    companion object{
        fun <T> ok(body: T): ResponseEntity<T> = ResponseEntity(200, body)
        fun notFound(): ResponseEntity<Nothing> = ResponseEntity(404)
        fun badRequest(): ResponseEntity<Nothing> = ResponseEntity(400)
        fun internalServerError(): ResponseEntity<Nothing> = ResponseEntity(500)
    }
}