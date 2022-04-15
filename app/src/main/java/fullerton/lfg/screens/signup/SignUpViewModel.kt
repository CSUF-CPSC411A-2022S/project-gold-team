package fullerton.lfg.screens.signup

import android.util.Patterns
import androidx.lifecycle.*
import fullerton.lfg.R
import fullerton.lfg.data.Result
import fullerton.lfg.data.UserInfo
import fullerton.lfg.data.model.LoggedInUser
import fullerton.lfg.data.model.LoggedInUserView
import fullerton.lfg.data.model.User
import java.io.IOException


class SignUpViewModel(): ViewModel() {


    //val allProfiles: LiveData<List<Profile>> = repo.allProfiles.asLiveData()

    private val _signupForm = MutableLiveData<SignUpFormState>()
    val signupFormState: LiveData<SignUpFormState> = _signupForm

    private val _signupResult = MutableLiveData<SignupResult>()
    val signupResult: LiveData<SignupResult> = _signupResult



    fun createUser(firstName: String, lastName: String, username: String,
                   password: String, confirmPassword: String) {
        // can be launched in a separate asynchronous job
        val result = checkResult(firstName,lastName,username,password)

        if (result is Result.Success) {
            _signupResult.value =
                SignupResult(success = LoggedInUserView(displayName = result.data.displayName))

        } else {
            _signupResult.value = SignupResult(error = R.string.signup_failed)
        }
    }

    private fun checkIfUserExists(firstName: String, lastName: String, username: String,
                                  password: String): Result<LoggedInUser>{
        val userInfo = UserInfo.dataSet()
        return try {
            lateinit var loggedInUser: LoggedInUser
            for (user in userInfo) {
                if (username != user.userId && username.isNotBlank()) {

                    userInfo.add(
                        User(
                            firstName = firstName,
                            lastName = lastName,
                            userId = username,
                            password = password,
                            displayName = firstName
                        )


                    )

                    loggedInUser = LoggedInUser(
                        userId = username,
                        displayName = firstName
                    )
                }
            }
            val createdUser = loggedInUser
            Result.Success(createdUser)
        } catch (e: Throwable) {
            Result.Error(IOException("This email already exists", e))
        }
    }

    private fun checkResult(firstName: String, lastName: String, username: String,
                            password: String): Result<LoggedInUser> {
        val result = checkIfUserExists(firstName,lastName,username
            , password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
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

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
    }
}

//class SignUpViewModelFactory(private val repo: ProfileRepo) : ViewModelProvider.Factory {
    //override fun <T : ViewModel> create(modelClass: Class<T>): T {
       // if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
           // @Suppress("UNCHECKED_CAST")
            //return SignUpViewModel(repo) as T
        //}
        //throw IllegalArgumentException("Unknown ViewModel class")
    //}
//}