package fullerton.lfg.data

import fullerton.lfg.data.model.LoggedInUser
import java.io.IOException

class LoginDataSource {

    // username is the username and password is the username input?
    fun login(username: String, password: String): Result<LoggedInUser> {

        return if (username.toString() == TestProfile.userId.toString() && password.toString() == TestProfile.password) {
            val fakeUser = LoggedInUser(TestProfile.userId.toString(),
                TestProfile.displayName.toString()

            )
            Result.Success(fakeUser)
        }else{
            Result.Error(IOException("Error logging in"))
        }


    }

    fun logout() {
    }
}