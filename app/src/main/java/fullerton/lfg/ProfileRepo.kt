package fullerton.lfg

import androidx.lifecycle.LiveData
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDao

class ProfileRepo(private val profileDao: ProfileDao) {
    suspend fun insertProfiles(profile: Profile) = profileDao.insert(profile)

    suspend fun updateProfile(profile: Profile) = profileDao.update(profile)

    fun getAllProfiles(): LiveData<List<Profile>> = profileDao.getAllProfiles()
}