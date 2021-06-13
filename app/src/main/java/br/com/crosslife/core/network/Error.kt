package br.com.crosslife.core.network

data class Error(
    val error: Throwable,
    val status: Status,
)