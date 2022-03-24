package fullerton.lfg.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import fullerton.lfg.ui.login.LoginViewModel
import fullerton.lfg.data.TestProfile
import fullerton.lfg.databinding.LoginBinding

/**
 * A simple [Fragment] subclass.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment() {

    // Binding object instance with access to the views in the login.xml layout
    private lateinit var binding: LoginBinding

    // Create a ViewModel the first time the fragment is created.
    // If the fragment is re-created, it receives the same LoginViewModel instance
    // created by the first fragment.
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate and bind the login.xml layout for the Login fragment
        binding = LoginBinding.inflate(layoutInflater)

        val username = binding.username
        val password = binding.password
        val login = binding.loginButton
        val signup = binding.signupButton

        // disable login button unless both username / password is valid


        /**
         * setOnClickerListener to the login_button that navigates from the
         * Login to LoggedIn fragment
         */

        login.setOnClickListener { view: View ->
            // show the LoggedIn fragment if login is successful
            if (username.text.toString() == TestProfile.userId && password.text.toString() == TestProfile.password) {
                view.findNavController().navigate(fullerton.lfg.ui.login.LoginDirections.actionLoginToLoggedIn())
            } else {
                // show an error message of some sort if not successful
                // and ask to the user to login again
                view.findNavController().navigate(fullerton.lfg.ui.login.LoginDirections.actionLoginSelf())

            }


        }
        return binding.root
    }
}