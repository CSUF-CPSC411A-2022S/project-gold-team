package fullerton.lfg

import androidx.annotation.WorkerThread
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDao
import kotlinx.coroutines.flow.Flow


class ProfileRepo(private val profileDao: ProfileDao) {
    suspend fun insertProfiles(profile: Profile) = profileDao.insert(profile)

    suspend fun updateProfile(profile: Profile) = profileDao.update(profile)

    val allProfiles: Flow<List<Profile>> = profileDao.getAllProfiles()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(profile: Profile) {
        profileDao.insert(profile)
    }

}