package fullerton.lfg.screens.userProfile

import android.app.Application
import androidx.lifecycle.*
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDao
import android.util.Log


class UserProfileViewModel(
    private val database: ProfileDao,
    application: Application,
) : AndroidViewModel(application){

    fun getUserProfile(email: String) : LiveData<Profile>{
        Log.i("test upmvmodel email", email)
        var profile = database.getProfile(email)
        Log.i("test upmvmodel profile", profile.value.toString())
        return profile
    }

}