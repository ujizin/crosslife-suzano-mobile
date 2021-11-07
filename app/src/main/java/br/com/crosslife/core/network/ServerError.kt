package br.com.crosslife.core.network

data class ServerError(
    val error: Throwable,
    val status: Status,
)