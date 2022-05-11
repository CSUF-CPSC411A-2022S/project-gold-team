package fullerton.lfg.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application): AndroidViewModel(application) {

    private val readAllProfile: LiveData<List<Profile>>
    private val repository: ProfileRepository

    init {
        val profileDao = ProfileDatabase.getInstance(application).profileDao()
        repository = ProfileRepository(profileDao)
        readAllProfile = repository.readAllData
    }

    fun addProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProfile(profile)
        }
    }
}