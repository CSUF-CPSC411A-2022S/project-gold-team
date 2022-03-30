package fullerton.lfg.ui.login


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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import fullerton.lfg.R
import fullerton.lfg.databinding.LoginBinding



class Login : Fragment() {

    private var binding: LoginBinding? = null

    private val loginViewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val loginBinding = LoginBinding.inflate(inflater, container, false)
        binding = loginBinding

        return loginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.logIn = this

        val username = binding?.username
        val password = binding?.password
        val login = binding?.loginButton
        val loading = binding?.loading

        loginViewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login?.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username?.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password?.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(viewLifecycleOwner, Observer {
            val loginResult = it ?: return@Observer

            loading?.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {

                findNavController().navigate(R.id.action_login_to_loggedIn)
                onDestroyView()
            }

            //Complete and destroy login activity once successful
        })

        username?.afterTextChanged {
            loginViewModel.loginDataChanged(
                username?.text.toString(),
                password?.text.toString()
            )
        }

        password?.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username?.text.toString(),
                    password?.text.toString()
                )
            }

            this?.setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username?.text.toString(),
                            password?.text.toString()
                        )
                }
                false
            }
            // login button
            login?.setOnClickListener {
                loading?.visibility = View.VISIBLE
                loginViewModel.login(username?.text.toString(), password.text.toString())
            }
        }
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
