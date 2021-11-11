package br.com.crosslife.domain.model

abstract class GenericError: Throwable()

class EmptyError: GenericError()

class PasswordNotEqualsError: GenericError()