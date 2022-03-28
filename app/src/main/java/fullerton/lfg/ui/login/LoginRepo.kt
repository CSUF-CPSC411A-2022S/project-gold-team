package fullerton.lfg.ui.login

import fullerton.lfg.data.LoginDataSource
import fullerton.lfg.data.Result
import fullerton.lfg.data.model.LoggedInUser

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
        LoginDataSource().logout()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        val result = LoginDataSource().login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }
        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }
}