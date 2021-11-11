package br.com.crosslife.network

data class ServerError(
    val error: Throwable,
    val status: Status,
)