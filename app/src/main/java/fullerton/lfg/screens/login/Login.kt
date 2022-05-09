package fullerton.lfg.screens.login


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.fragment.findNavController
import fullerton.lfg.R
import fullerton.lfg.data.model.LoggedInUserView
import fullerton.lfg.database.ProfileDatabase

import fullerton.lfg.databinding.LoginBinding


class Login : Fragment() {

    private var binding: LoginBinding? = null

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginBinding: LoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        loginBinding = LoginBinding.inflate(inflater, container, false)
        binding = loginBinding

        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = ProfileDatabase.getInstance(application).profileDao()

        val viewModelFactory = LoginModelFactory(dataSource, application)
        loginViewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        binding?.logIn = this

        val email = binding?.email
        val password = binding?.password
        val login = binding?.loginButton
        val loading = binding?.loading
        val signup = binding?.signupButton

        loginViewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login?.isEnabled = loginState.isDataValid

            if (loginState.emailError != null) {
                email?.error = getString(loginState.emailError)
            }
            if (loginState.passwordError != null) {
                password?.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.allProfiles.observe(viewLifecycleOwner){
            profiles ->
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner, Observer {
            val loginResult = it ?: return@Observer

            loading?.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
        })

        email?.afterTextChanged {
            loginViewModel.loginDataChanged(
                email?.text.toString(),
                password?.text.toString()
            )
        }

        password?.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    email?.text.toString(),
                    password?.text.toString()
                )
            }

            this.setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            email?.text.toString(),
                            password?.text.toString()
                        )
                }
                false
            }
            // login button
            login?.setOnClickListener {
                loading?.visibility = View.VISIBLE
                loginViewModel.login(email?.text.toString(), password.text.toString())
                onDestroyView()
            }

            signup?.setOnClickListener{
                findNavController().navigate(R.id.action_login_to_signUp)
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView?) {
        val welcome = getString(R.string.welcome)
        val displayName = model?.displayName
        val email = model?.email
        //Toast.makeText(
            //requireContext(),
            //"$welcome $displayName",
            //Toast.LENGTH_LONG
        //).show()
        val action = LoginDirections.actionLoginToLoggedIn(displayName!!)
        findNavController().navigate(action)
        onDestroyView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(activity, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
