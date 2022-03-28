package fullerton.lfg.ui.login

import fullerton.lfg.ui.loggedin.LoggedInUserView

data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)
