package br.com.yujiyoshimine.domain.model

data class ServerError(
    val error: Throwable,
    val status: Status,
): Throwable()