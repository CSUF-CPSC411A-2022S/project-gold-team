package fullerton.lfg.data

import fullerton.lfg.data.model.LoggedInUser
import fullerton.lfg.data.model.User
import java.io.IOException

class TestDataSource {


    fun createUser(firstName: String, lastName: String, username: String,
                   password: String): Result<User>{
        return try {
            val userInfo = UserInfo.dataSet()
            lateinit var user: User
            for (users in userInfo) {
                if (username != users.userId && username.isNotBlank()) {

                    userInfo.add(
                        User(
                            firstName = firstName,
                            lastName = lastName,
                            userId = username,
                            password = password,
                            displayName = firstName
                        )
                    )
                    user = User(
                        firstName = firstName,
                        lastName = lastName,
                        userId = username,
                        password = password,
                        displayName = firstName
                    )
                    break
                }
            }
            val createdUser = user
            Result.Success(createdUser)
        } catch (e: Throwable) {
            Result.Error(IOException("This email already exists", e))
        }


    }

    fun logout() {

    }
}