package br.com.crosslife.domain.model

enum class Status(val code: Int) {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    UNPROCESSED(422),
    INTERNAL_ERROR(500),
    UNKNOWN(-1),
    NO_NETWORK(-2);

    companion object {
        fun getStatus(code: Int) = values().firstOrNull { it.code == code } ?: UNKNOWN
    }
}