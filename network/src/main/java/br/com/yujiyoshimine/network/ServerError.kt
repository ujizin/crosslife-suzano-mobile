package br.com.yujiyoshimine.network

data class ServerError(
    val error: Throwable,
    val status: Status,
)