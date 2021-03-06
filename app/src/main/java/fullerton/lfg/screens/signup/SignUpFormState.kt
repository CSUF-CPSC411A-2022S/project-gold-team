// Zulema Perez
// CSPC 411

package fullerton.lfg.screens.signup

data class SignUpFormState(
    val firstNameError: Int? = null,
    val lastNameError: Int? = null,
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val confirmPasswordError: Int? = null,
    val isDataValid: Boolean = false
)
