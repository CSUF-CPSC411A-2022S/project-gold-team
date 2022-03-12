package fullerton.lfg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import fullerton.lfg.data.TestProfile
import fullerton.lfg.databinding.LoginBinding

/**
 * A simple [Fragment] subclass.
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate and bind the login.xml layout for the Login fragment
        val binding = LoginBinding.inflate(layoutInflater)

        val username = binding.username
        val password = binding.password
        val login = binding.loginButton

        // disable login button unless both username / password is valid


        /**
         * setOnClickerListener to the login_button that navigates from the
         * Login to LoggedIn fragment
         */

        binding.loginButton.setOnClickListener { view: View ->
            // show the LogginIn fragment if login is successful
            if (username.text.toString() == TestProfile.userId && password.text.toString() == TestProfile.password) {
                view.findNavController().navigate(LoginDirections.actionLoginToLoggedIn())
            }
            // show an error message of some sort if not successful
            // and ask to the user to login again

        }
        return binding.root
    }
}