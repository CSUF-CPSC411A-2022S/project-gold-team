package fullerton.lfg.data

import fullerton.lfg.data.model.LoggedInUser
import fullerton.lfg.data.model.User
import java.io.IOException

class DataSource {
    fun login(username: String, password: String): Result<LoggedInUser> {
        return try {
            val userInfo = UserInfo.dataSet()
            lateinit var loggedInUser: LoggedInUser
            for (user in userInfo) {

                if (user.userId == username && user.password == password){
                    loggedInUser = LoggedInUser(
                        userId = user.userId,
                        displayName = user.displayName
                    )
                    break
                }
            }
            val foundUser = loggedInUser
            Result.Success(foundUser)
        } catch (e: Throwable) {
            Result.Error(IOException("Error logging in", e))
        }
    }

    fun createUser(firstName: String, lastName: String, username: String,
                   password: String): Result<LoggedInUser>{
        return try {
            val userInfo = UserInfo.dataSet()
            lateinit var loggedInUser: LoggedInUser
            for (user in userInfo) {
                if (username != user.userId && username.isNotBlank()) {

                    userInfo.add(
                        User(
                            firstName = firstName,
                            lastName = lastName,
                            userId = username,
                            password = password,
                            displayName = firstName
                        )
                    )
                    loggedInUser = LoggedInUser(
                        userId = username,
                        displayName = firstName
                    )
                    break
                 }
            }
            val createdUser = loggedInUser
            Result.Success(createdUser)
        } catch (e: Throwable) {
            Result.Error(IOException("This email already exists", e))
        }


    }

    fun logout() {

    }
}