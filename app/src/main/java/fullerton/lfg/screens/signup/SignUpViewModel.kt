package fullerton.lfg.screens.signup


import android.app.Application
import android.util.Patterns

import androidx.lifecycle.*
import fullerton.lfg.R

import fullerton.lfg.database.Profile
import android.util.Log
import fullerton.lfg.data.model.User
import fullerton.lfg.database.ProfileDao

import kotlinx.coroutines.launch

class SignUpViewModel(
    private val database: ProfileDao,
    application: Application
) : ViewModel() {


    init {
        Log.i("Testing", "init")
    }


    private val _signupForm = MutableLiveData<SignUpFormState>()
    val signupFormState: LiveData<SignUpFormState> = _signupForm

    private val _signupResult = MutableLiveData<SignupResult>()
    val signupResult: LiveData<SignupResult> = _signupResult

    private val _userDetail = MutableLiveData<Profile>()
    val userDetail: LiveData<Profile> = _userDetail

    fun createUser(
        firstname: String, lastname: String, username: String,
        password: String, confirmPassword: String
    ) {

        // can be launched in a separate asynchronous job
        Log.i("Testing", "Inside createUser function")
        val result = checkIfUserExists(username)
        Log.i("Testing", result?.value?.username + " <- result in createUser")
        var userDetail = Transformations.map(result){
            results ->
            var user = ""
            user += results.username
            Log.i("Testing", results.username + " <- results.username")
            user
        }


        Log.i("Testing", userDetail.value + " <- userDetail")
        if (userDetail.value == null) {
            Log.i("Testing", "Inside result == null")

            _signupResult.value =
                SignupResult(
                    success = User(
                        firstName = firstname, lastName = lastname,
                        userId = username, password = password
                    )
                )

        } else if (userDetail.value != null) {
            _signupResult.value = SignupResult(error = R.string.signup_failed)
            Log.i("Testing", "Inside result == else")
        }
    }

    private fun checkIfUserExists(username: String): LiveData<Profile> {
        Log.i("Testing", "Inside checkIfUserExists function")
        return database.getProfile(username)
    }

    fun insert(
        firstname: String, lastname: String, username: String,
        password: String
    ) {
        Log.i("Testing", "Inside createProfile function")

        Log.i("Testing", "username: $username")
        viewModelScope.launch {
            val profile = Profile()
            profile.firstname = firstname
            profile.lastname = lastname
            profile.username = username
            profile.password = password
            Log.i("Testing", "username: " + profile.username)
            database.insert(profile)
        }
    }

    fun signupDataChanged(
        firstName: String, lastName: String, username: String,
        password: String, confirmPassword: String
    ) {
        if (!isFirstNameValid(firstName)) {
            _signupForm.value = SignUpFormState(firstNameError = R.string.invalid_firstname)
        } else if (!isLastNameValid(lastName)) {
            _signupForm.value = SignUpFormState(lastNameError = R.string.invalid_lastname)
        } else if (!isUserNameValid(username)) {
            _signupForm.value = SignUpFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _signupForm.value = SignUpFormState(passwordError = R.string.invalid_password)
        } else if (!isConfirmPasswordValid(password, confirmPassword)) {
            _signupForm.value =
                SignUpFormState(confirmPasswordError = R.string.invalid_confirm_password)
        } else {
            _signupForm.value = SignUpFormState(isDataValid = true)
        }
    }

    private fun isFirstNameValid(firstName: String): Boolean {
        return firstName.isNotEmpty() && firstName != " "

    }

    private fun isLastNameValid(lastName: String): Boolean {
        return lastName.isNotEmpty() && lastName != " "

    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    private fun isConfirmPasswordValid(password: String, confirmPassword: String): Boolean {
        return confirmPassword.length > 5 && password == confirmPassword
    }
}
