package fullerton.lfg.screens.signup

import android.app.Application
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fullerton.lfg.R
import fullerton.lfg.data.model.User
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDao
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val database: ProfileDao,
    application: Application
) : AndroidViewModel(application) {


    init {
        Log.i("Testing", "init")

    }

    val allProfiles: LiveData<List<Profile>> = database.getAllProfiles()
    val getProfile: LiveData<Profile>? = isUserName?.let { database.getProfile(it) }

    private val _signupForm = MutableLiveData<SignUpFormState>()
    val signupFormState: LiveData<SignUpFormState> = _signupForm

    private val _signupResult = MutableLiveData<SignupResult>()
    val signupResult: LiveData<SignupResult> = _signupResult


    private var userName: String? = null
    private val isUserName: String? get() = userName

    private fun setUserName(username: String) {
        this.userName = username
    }

    fun signUpUser(
        firstname: String, lastname: String, email: String,
        password: String
    ) {
// can be launched in a separate asynchronous job
        Log.i("Testing", "Inside createUser function")
        setUserName(email)
        val checkResult = checkIfUserExists(email)


        Log.i("Testing", getProfile?.value?.firstname + " <- Inside getProfile function")

        Log.i("Testing", "$checkResult<- Inside signUpUser function")

        if (checkResult) {
            Log.i("Testing", "Inside checkResult == true")

            _signupResult.value =
                SignupResult(
                    success = User(
                        firstName = firstname, lastName = lastname,
                        email = email, password = password
                    )
                )

        } else if (!checkResult) {
            _signupResult.value = SignupResult(error = R.string.signup_failed)
            Log.i("Testing", "Inside checkResult == false")
        }
    }


    private fun checkIfUserExists(email: String): Boolean {
        var result = ""
        if (allProfiles.value.isNullOrEmpty()) {
            result = true.toString()
        } else {
            for (profile in allProfiles.value!!) {
                Log.i(
                    "Testing",
                    profile.email + " " + email + " <- Inside checkIfUserExists function for loop"
                )
                if (profile.email == email) {
                    Log.i(
                        "Testing",
                        profile.email + " " + email + " <- Inside checkIfUserExists function for loop"
                    )
                    result = false.toString()
                    break

                } else {
                    result = true.toString()
                }

            }
        }
        Log.i("Testing", "$result <- Inside checkIfUserExists function result")
        return result.toBoolean()
    }

    fun insert(
        firstname: String, lastname: String, email: String,
        password: String
    ) {
        Log.i("Testing", "Inside createProfile function")

        Log.i("Testing", "username: $email")
        viewModelScope.launch {
            val profile = Profile()
            profile.firstname = firstname
            profile.lastname = lastname
            profile.email = email
            profile.password = password
            Log.i("Testing", "username: " + profile.email)
            database.addProfile(profile)
        }
    }

    fun signupDataChanged(
        firstName: String, lastName: String, email: String,
        password: String, confirmPassword: String
    ) {
        if (!isFirstNameValid(firstName)) {
            _signupForm.value = SignUpFormState(firstNameError = R.string.invalid_firstname)
        } else if (!isLastNameValid(lastName)) {
            _signupForm.value = SignUpFormState(lastNameError = R.string.invalid_lastname)
        } else if (!isUserNameValid(email)) {
            _signupForm.value = SignUpFormState(emailError = R.string.invalid_email)
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