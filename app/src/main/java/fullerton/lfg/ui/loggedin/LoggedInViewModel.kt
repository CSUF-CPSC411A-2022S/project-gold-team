package fullerton.lfg.ui.loggedin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fullerton.lfg.data.TestProfile


class LoggedInViewModel : ViewModel() {

    private val _loggedInDisplayName = MutableLiveData<String>()
    val loggedInDisplayName: LiveData<String> = _loggedInDisplayName

  fun loggedInUserView() {
      _loggedInDisplayName.value = TestProfile.displayName
    }
}