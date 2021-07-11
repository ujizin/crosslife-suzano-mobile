package br.com.crosslife.extensions


fun String.capitalize() = replaceFirstChar { it.uppercase() }