package fullerton.lfg.screens.signup

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fullerton.lfg.ProfileRepo

class SignUpViewModelFactory(
    private val repo: ProfileRepo,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignUpViewModel(repo,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}