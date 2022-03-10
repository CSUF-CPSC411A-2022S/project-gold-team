package fullerton.lfg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        return binding.root
    }
}