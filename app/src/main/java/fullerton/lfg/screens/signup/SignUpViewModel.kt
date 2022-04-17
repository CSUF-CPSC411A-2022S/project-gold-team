package fullerton.lfg.screens.signup

import android.app.Application
import android.util.Patterns
import androidx.databinding.Observable
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import fullerton.lfg.R
import fullerton.lfg.data.model.LoggedInUser
import fullerton.lfg.data.model.LoggedInUserView
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDao
import kotlinx.coroutines.launch


class SignUpViewModel(
    val database: ProfileDao,
    application: Application
): AndroidViewModel(application) {


    val profileList = database.getAllProfiles()


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
        val result = checkResult(firstname,lastname,username,password)

        if (result == false) {
            _signupResult.value =
                SignupResult(success = LoggedInUserView(displayName = firstname))


        } else {
            _signupResult.value = SignupResult(error = R.string.signup_failed)
        }
    }

    private fun checkIfUserExists(
        firstname: String, lastname: String, username: String,
        password: String
    ): Boolean {

        return database.isProfileExist(username)
    }

    private fun checkResult(firstname: String, lastname: String, username: String,
                            password: String): Boolean {
        val result = checkIfUserExists(firstname,lastname,username
            , password)

        if (result == false) {
            setLoggedInUser(LoggedInUser(userId = username, displayName = firstname))
            _firstName.value = firstname
            _lastName.value = lastname
            _userName.value = username
            _password.value = password
            insert()
        }
        return result
    }

    private fun insert() {
        viewModelScope.launch {
            var profile = Profile()
            profile.firstname = firstName.value.toString()
            profile.lastname = lastName.value.toString()
            profile.username = userName.value.toString()
            profile.password = password.value.toString()

            database.insert(profile)
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
    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        user = null
    }

    fun logout() {
        user = null

    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser?) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
    }
}
