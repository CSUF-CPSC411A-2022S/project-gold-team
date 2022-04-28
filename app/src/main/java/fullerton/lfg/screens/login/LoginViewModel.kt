package fullerton.lfg.screens.login

import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fullerton.lfg.R
import fullerton.lfg.data.model.LoggedInUserView
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDao

class LoginViewModel(
    private val database: ProfileDao,
    application: Application
) : AndroidViewModel(application) {

    val allProfiles: LiveData<List<Profile>> = database.getAllProfiles()

    private val _userDetail = MutableLiveData<Profile>()
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {

        val result = checkIfUserExists(username, password)

        if (result == true) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = _userDetail.value?.firstname!!))

        } else if (result == false) {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    private fun checkIfUserExists(username: String, password: String): Boolean {
        var result = ""
        if (allProfiles.value.isNullOrEmpty()) {
            result = false.toString()
        } else {
            for (profile in allProfiles.value!!) {
                Log.i(
                    "Testing",
                    profile.username + " " + username + " <- Inside checkIfUserExists function for loop"
                )
                if (profile.username == username && profile.password == password) {
                    Log.i(
                        "Testing",
                        profile.username + " " + username + " <- Inside checkIfUserExists function for loop"
                    )
                    _userDetail.value = profile
                    result = true.toString()
                    break

                } else {
                    result = false.toString()
                }

            }
        }
        Log.i("Testing", "$result <- Inside checkIfUserExists function result")
        return result.toBoolean()
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}