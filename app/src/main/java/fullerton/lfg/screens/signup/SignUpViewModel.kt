package fullerton.lfg.screens.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fullerton.lfg.R
import fullerton.lfg.data.LoginRepo
import fullerton.lfg.data.Result
import fullerton.lfg.data.model.LoggedInUserView

class SignUpViewModel(): ViewModel() {

    private val _signupForm = MutableLiveData<SignUpFormState>()
    val signupFormState: LiveData<SignUpFormState> = _signupForm

    private val _signupResult = MutableLiveData<SignupResult>()
    val signupResult: LiveData<SignupResult> = _signupResult

    fun createUser(firstName: String, lastName: String, username: String,
                   password: String, confirmPassword: String){
        // can be launched in a separate asynchronous job
        val result = LoginRepo().createUser(firstName,lastName,username
            , password,confirmPassword)

        if (result is Result.Success) {
            _signupResult.value =
                SignupResult(success = LoggedInUserView(displayName = result.data.displayName))

        } else {
            _signupResult.value = SignupResult(error = R.string.signup_failed)
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