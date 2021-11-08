package br.com.yujiyoshimine.domain.model

abstract class GenericError: Throwable()

class EmptyError: GenericError()

class PasswordNotEqualsError: GenericError()