package fullerton.lfg.ui.login

import fullerton.lfg.data.Result
import fullerton.lfg.data.TestProfile
import fullerton.lfg.data.model.LoggedInUser
import java.io.IOException

class LoginRepo {

    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout() {
        user = null
        logoutDataSource()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        val result = loginDataSource(username,password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }
        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }

    private fun loginDataSource(username: String, password: String): Result<LoggedInUser> {

        return if (username.toString() == TestProfile.userId.toString() && password.toString() == TestProfile.password) {
            val fakeUser = LoggedInUser(
                TestProfile.userId.toString(),
                TestProfile.displayName.toString()

            )
            Result.Success(fakeUser)
        }else{
            Result.Error(IOException("Error logging in"))
        }


    }

    private fun logoutDataSource() {
    }

    private fun loggedInUser(){

    }
}