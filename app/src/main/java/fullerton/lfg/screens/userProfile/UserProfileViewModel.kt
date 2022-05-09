package fullerton.lfg.screens.userProfile

import android.app.Application
import androidx.lifecycle.*
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDao

class UserProfileViewModel(
    private val database: ProfileDao,
    private val savedStateHandle: SavedStateHandle,
    application: Application
) : AndroidViewModel(application){

     var _userProfile : LiveData<Profile> =
        savedStateHandle.getLiveData<String>("email").switchMap { email ->
            database.getProfile(email)
        }



}