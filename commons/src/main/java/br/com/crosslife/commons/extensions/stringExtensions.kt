package br.com.crosslife.commons.extensions


fun String.capitalize() = replaceFirstChar { it.uppercase() }