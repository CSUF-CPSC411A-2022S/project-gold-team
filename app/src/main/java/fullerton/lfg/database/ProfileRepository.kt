package fullerton.lfg.database

import androidx.lifecycle.LiveData

class ProfileRepository(private val profileDao: ProfileDao) {

    val readAllData: LiveData<List<Profile>> = profileDao.getAllProfiles()

    suspend fun addProfile(profile: Profile) {
        profileDao.addProfile(profile)
    }
}