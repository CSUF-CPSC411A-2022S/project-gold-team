package fullerton.lfg.screens.signup

import fullerton.lfg.database.Profile

data class SignupResult(
    val success: Profile? = null,
    val error: Int? = null
)
