package br.com.yujiyoshimine.network.dto

data class DetailProfileDTO(
    val fatMass: Int,
    val slimMass: Int,
    val muscleMass: Int,
    val hydration: Int,
    val boneDensity: Int,
    val visceralFat: Int,
    val basalMetabolism: Int,
)
