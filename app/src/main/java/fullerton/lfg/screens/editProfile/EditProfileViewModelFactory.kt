package fullerton.lfg.screens.editProfile

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fullerton.lfg.database.ProfileDao


class EditProfileViewModelFactory(
    private val database: ProfileDao,
    private val application: Application,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EditProfileViewModel(database, application ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}