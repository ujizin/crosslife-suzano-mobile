package br.com.crosslife.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instructor(
    val id: String,
    val name: String,
    val number: Int,
    val imageUrl: String
): Parcelable {

    companion object {
        const val INSTRUCTOR_ARG_KEY = "instructor"
    }
}

val InstructorOne = Instructor(
    "instrutor01",
    "Carlos",
    1,
    imageUrl = "https://armasterplus.com.br/wp-content/uploads/bfi_thumb/our-team-4-1-36g5tt9lpndi5omesjwtfu@2x.jpg"
)

val InstructorTwo = Instructor(
    "instrutor02",
    "Bruno",
    2,
    imageUrl = "https://5ba9963e93153e603e786438.static-01.com/l/images/a3bb662d840efe6219c2ff7d8099da8af3b6c41f.jpg"
)