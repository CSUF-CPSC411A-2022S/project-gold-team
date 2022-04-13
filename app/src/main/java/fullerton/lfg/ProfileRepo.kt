package fullerton.lfg

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import fullerton.lfg.data.DataSource
import fullerton.lfg.data.Result
import fullerton.lfg.data.TestDataSource
import fullerton.lfg.data.model.LoggedInUser
import fullerton.lfg.data.model.User
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDao
import kotlinx.coroutines.flow.Flow

class ProfileRepo(private val profileDao: ProfileDao) {
    suspend fun insertProfiles(profile: Profile) = profileDao.insert(profile)

    suspend fun updateProfile(profile: Profile) = profileDao.update(profile)

    val allProfiles: LiveData<List<Profile>> = profileDao.getAllProfiles()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(profile: Profile) {
        profileDao.insert(profile)
    }

    // in-memory cache of the loggedInUser object
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        user = null
    }

    fun logout() {
        user = null
        TestDataSource().logout()
    }



    fun createUser(firstName: String, lastName: String, username: String,
                   password: String, confirmPassword: String): Result<User> {
        val result = TestDataSource().createUser(firstName,lastName,username
            , password)

        if (result is Result.Success) {
            setUser(result.data)
        }

        return result
    }

    private fun setUser(user: User) {
        this.user = user
        // If user credentials will be cached in local storage, it is recommended it be encrypted
    }


}