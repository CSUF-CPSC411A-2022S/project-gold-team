package fullerton.lfg.screens.signup

import fullerton.lfg.data.model.LoggedInUserView
import fullerton.lfg.data.model.User

data class SignupResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)
