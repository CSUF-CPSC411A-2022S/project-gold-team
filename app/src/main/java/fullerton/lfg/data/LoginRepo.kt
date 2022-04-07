package fullerton.lfg.data

import fullerton.lfg.data.model.LoggedInUser


class LoginRepo() {

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        user = null
    }

    fun logout() {
        user = null
        DataSource().logout()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = DataSource().login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    fun createUser(firstName: String, lastName: String, username: String,
                   password: String, confirmPassword: String): Result<LoggedInUser> {
        val result = DataSource().createUser(firstName,lastName,username
            , password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
    }
}