// Zulema Perez
// CSPC 411
package fullerton.lfg.screens.signup

import fullerton.lfg.data.model.User

data class SignupResult(
    val success: User? = null,
    val error: Int? = null
)
