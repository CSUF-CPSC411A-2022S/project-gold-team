package fullerton.lfg.screens.signup

import fullerton.lfg.data.model.LoggedInUserView

data class SignupResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)
