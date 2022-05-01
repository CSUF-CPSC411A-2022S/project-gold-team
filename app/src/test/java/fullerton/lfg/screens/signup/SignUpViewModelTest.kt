package fullerton.lfg.screens.signup

import fullerton.lfg.R
import junit.framework.TestCase
import org.junit.Test

class SignUpViewModelTest : TestCase() {

    @Test
    fun testGetSignupFormState() {
        val firstNameError = R.string.invalid_firstname
        val lastNameError = R.string.invalid_lastname
        val usernameError = R.string.invalid_username
        val passwordError = R.string.invalid_password
        val confirmPasswordError = R.string.invalid_confirm_password

        val signUpForm = SignUpFormState(
            firstNameError, lastNameError, usernameError, passwordError, confirmPasswordError
        )

        assertEquals(R.string.invalid_firstname,signUpForm.firstNameError)
        assertEquals(R.string.invalid_lastname,signUpForm.lastNameError)
        assertEquals(R.string.invalid_username,signUpForm.usernameError)
        assertEquals(R.string.invalid_password,signUpForm.passwordError)

    }
    @Test
    fun testGetSignupResult() {}
    @Test
    fun testSignUpUser() {}
    @Test
    fun testSignupDataChanged() {}
}