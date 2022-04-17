package fullerton.lfg.screens.signup


import android.app.Application
import android.util.Patterns

import androidx.lifecycle.*
import fullerton.lfg.ProfileRepo
import fullerton.lfg.R
import fullerton.lfg.data.model.LoggedInUser
import fullerton.lfg.data.model.LoggedInUserView
import fullerton.lfg.database.Profile
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class SignUpViewModel(
    private val repo: ProfileRepo,
    application: Application
): AndroidViewModel(application) {


    init {
        Log.i("Testing", "init")
    }

    val allProfiles: LiveData<List<Profile>> = repo.allProfiles
    private val _userDetails = MutableLiveData<Profile>()
    val userDetails: LiveData<Profile> = _userDetails


    private val _signupForm = MutableLiveData<SignUpFormState>()
    val signupFormState: LiveData<SignUpFormState> = _signupForm

    private val _signupResult = MutableLiveData<SignupResult>()
    val signupResult: LiveData<SignupResult> = _signupResult

    private val _firstName = MutableLiveData("")
    val firstName: LiveData<String> = _firstName
    private val _lastName = MutableLiveData("")
    val lastName: LiveData<String> = _lastName
    private val _userName = MutableLiveData("")
    val userName: LiveData<String> = _userName
    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password



    fun createUser(firstname: String, lastname: String, username: String,
                   password: String, confirmPassword: String) {
        // can be launched in a separate asynchronous job
        Log.i("Testing", "Inside createUser function")
        val result = checkIfUserExists(username)
        Log.i("Testing", result?.value?.username + " <- result in createUser")

        if (result?.value?.username == null) {
            _signupResult.value =
                SignupResult(success = LoggedInUserView(displayName = firstname))

            Log.i("Testing", "Inside result == null")
            createProfile(firstname, lastname, username, password)

        } else if (result?.value?.username != null) {
            _signupResult.value = SignupResult(error = R.string.signup_failed)
            Log.i("Testing", "Inside result == else")
        }
    }

    private fun checkIfUserExists(username: String): LiveData<Profile>? {
        Log.i("Testing", "Inside checkIfUserExists function")
        return repo.checkIfUserExists(username)
    }

    private fun createProfile(firstname: String, lastname: String, username: String,
                            password: String){
        Log.i("Testing", "Inside createProfile function")
        var profile = Profile()
        Log.i("Testing", "username: " + username)
        viewModelScope.launch {

            profile.firstname = firstname
            profile.lastname = lastname
            profile.username= username
            profile.password = password
            Log.i("Testing", "username: " + profile.username)
            repo.insert(profile)
        }
    }

    fun signupDataChanged(firstName: String, lastName: String, username: String,
                          password: String, confirmPassword: String) {
        if(!isFirstNameValid(firstName)) {
            _signupForm.value = SignUpFormState(firstNameError = R.string.invalid_firstname)
        }else if (!isLastNameValid(lastName)){
            _signupForm.value = SignUpFormState(lastNameError = R.string.invalid_lastname)
        } else if (!isUserNameValid(username)) {
            _signupForm.value = SignUpFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _signupForm.value = SignUpFormState(passwordError = R.string.invalid_password)
        } else if (!isConfirmPasswordValid(password,confirmPassword)) {
            _signupForm.value = SignUpFormState(confirmPasswordError = R.string.invalid_confirm_password)
        } else {
            _signupForm.value = SignUpFormState(isDataValid = true)
        }
    }

    private fun isFirstNameValid(firstName: String): Boolean {
        return firstName.length > 0 && firstName != " "

    }
    private fun isLastNameValid(lastName: String): Boolean {
        return lastName.length > 0 && lastName != " "

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
