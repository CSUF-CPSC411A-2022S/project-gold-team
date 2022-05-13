package fullerton.lfg.screens.editProfile

/*
    Since this viewModel deals exclusively with database transactions,
    we will forego unit tests as agreed with professor
 */

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDao
import kotlinx.coroutines.launch

class EditProfileViewModel(
    private val database: ProfileDao,
    application: Application
) : AndroidViewModel(application){

    fun getUserProfile(email: String) : LiveData<Profile> {
        return database.getProfile(email)
    }

    fun updateUserFirstName(email: String, newFirstName: String) {
        viewModelScope.launch {
            database.updateFirstName(email, newFirstName)
        }
    }

    fun updateUserLastName(email: String, newLastName: String) {
        viewModelScope.launch {
            database.updateLastName(email, newLastName)
        }
    }

    fun updateUserPassword(email: String, newPassword: String) {
        viewModelScope.launch {
            database.updatePassword(email, newPassword)
        }
    }
}