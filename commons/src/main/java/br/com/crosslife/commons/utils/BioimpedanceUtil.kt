package br.com.crosslife.commons.utils

object BioimpedanceUtil {

    enum class Format(val type: String) {
        Kilos("kg"),
        Milliliters("ml")
    }

    fun format(quantity: Int, format: Format) = "$quantity ${format.type}"
}
