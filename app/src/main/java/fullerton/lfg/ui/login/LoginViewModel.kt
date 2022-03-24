package fullerton.lfg.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _username = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    val username: LiveData<String>
        get() {
            return _username
        }

    val password: LiveData<String>
        get() {
            return _password
        }

    init {
        _username.value = null
    }
    fun login(username: String, password: String) {

    }
}