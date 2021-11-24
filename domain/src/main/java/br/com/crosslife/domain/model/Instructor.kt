package br.com.crosslife.domain.model

data class Instructor(
    val id: Int,
    val name: String,
    val number: Int
)

val InstructorOne = Instructor(
    1,
    "Carlos",
    1
)

val InstructorTwo = Instructor(
    1,
    "Bruno",
    2
)