package fullerton.lfg.screens.userProfile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fullerton.lfg.database.ProfileDao


class UserProfileViewModelFactory(
    private val database: ProfileDao,
    private val application: Application,
    private val email: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserProfileViewModel(database, application ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}