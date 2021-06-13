package br.com.crosslife.core.network

enum class Status(val code: Int) {
    BadRequest(Status.BAD_REQUEST),
    Unauthorized(Status.UNAUTHORIZED),
    Unprocessed(Status.UNPROCESSED),
    InternalError(Status.INTERNAL_ERROR),
    Unknown(Status.UNKNOWN),
    NoNetwork(Status.NO_NETWORK);

    companion object {
        private const val BAD_REQUEST = 400
        private const val UNAUTHORIZED = 401
        private const val UNPROCESSED = 422
        private const val INTERNAL_ERROR = 500
        private const val UNKNOWN = -1
        private const val NO_NETWORK = -2
    }
}