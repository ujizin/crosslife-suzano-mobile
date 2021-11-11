package br.com.crosslife.domain.model

data class ServerError(
    val error: Throwable,
    val status: Status,
): Throwable()