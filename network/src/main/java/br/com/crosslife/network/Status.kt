package br.com.crosslife.network

enum class Status(val code: Int) {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    UNPROCESSED(422),
    INTERNAL_ERROR(500),
    UNKNOWN(-1),
    NO_NETWORK(-2);
}