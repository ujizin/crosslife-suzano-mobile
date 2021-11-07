package br.com.yujiyoshimine.commons.extensions


fun String.capitalize() = replaceFirstChar { it.uppercase() }