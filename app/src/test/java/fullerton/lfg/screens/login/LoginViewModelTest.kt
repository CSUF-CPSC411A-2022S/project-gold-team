package fullerton.lfg.screens.login

import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

/*
Test cases here are mainly to test the loginFormState struct,
since it is not currently possible to setup a modelView test.

Once a way to setup a test modelView, other functions can be tested.
 */

class LoginViewModelTest : TestCase() {

    @Test
    fun testLoginFormState() = runBlocking {
        val loginForm1 = LoginFormState(1,
            1, false)
        assertEquals(1, loginForm1.usernameError)
        assertEquals(1, loginForm1.passwordError)
        assertFalse(loginForm1.isDataValid)
        val loginForm2 = LoginFormState(0,
            0,true)
        assertEquals(0, loginForm2.usernameError)
        assertEquals(0, loginForm2.passwordError)
        assertTrue(loginForm2.isDataValid)
    }
}