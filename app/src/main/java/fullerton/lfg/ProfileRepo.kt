package fullerton.lfg

import android.util.Log


import androidx.lifecycle.LiveData
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDao


class ProfileRepo(private val profileDao: ProfileDao) {

    fun checkIfUserExists(username: String): LiveData<Profile>? {
        Log.i("Testing", "Inside Repo checkIfUserExists function")
        val result = profileDao.getProfile(username)
        Log.i("Testing", result?.value?.username.toString() + " <- username")
        return result
        //return profileDao.isProfileExist(username = username)
    }
    suspend fun insert(profile: Profile) {
        profileDao.insert(profile)
    }

}