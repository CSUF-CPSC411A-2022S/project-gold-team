package fullerton.lfg.screens.signup

import fullerton.lfg.data.model.User
import fullerton.lfg.database.Profile

data class SignupResult(
    val success: User? = null,
    val error: Int? = null
)
