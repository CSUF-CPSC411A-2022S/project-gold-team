package fullerton.lfg

import android.util.Log
import androidx.annotation.WorkerThread

import androidx.lifecycle.LiveData
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDao


class ProfileRepo(private val profileDao: ProfileDao) {
    val allProfiles: LiveData<List<Profile>> = profileDao.getAllProfiles()

    fun checkIfUserExists(username: String): LiveData<Profile>? {
        Log.i("Testing", "Inside Repo checkIfUserExists function")
        val result = profileDao.getProfile(username)
        Log.i("Testing", result?.value?.username.toString() + " <- username")
        return result
        //return profileDao.isProfileExist(username = username)
    }
    //fun getProfile: Flow<Profile> = profileDao.getProfile()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(profile: Profile) {
        profileDao.insert(profile)
    }

}