// Zulema Perez
// CPSC 411

package fullerton.lfg.screens.login

data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)
