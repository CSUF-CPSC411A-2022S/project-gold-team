package fullerton.lfg.screens.login

import fullerton.lfg.data.model.LoggedInUserView

data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)
