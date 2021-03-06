// Zulema Perez
// CPSC 411
// Created loggedin fragment for group members to connect thier fragments.

package fullerton.lfg.screens.loggedin


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import fullerton.lfg.R
import fullerton.lfg.databinding.LoggedInBinding


class LoggedIn : Fragment() {

    private var binding: LoggedInBinding? = null
    val args: LoggedInArgs by navArgs()
    private lateinit var loggedInBinding: LoggedInBinding

    private val loggedInViewModel: LoggedInViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loggedInBinding = LoggedInBinding.inflate(inflater, container, false)
        binding = loggedInBinding

        return loggedInBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            loggedInviewModel = loggedInViewModel
            loggedIn = this@LoggedIn
        }

        val loggedin = args.loggedin
        val username = args.username
        val greet = binding?.greeting
        val createEvent = binding?.createEvent
        val browseEvent = binding?.browseEvent
        val logOut = binding?.logOut
        val profile = binding?.profile
        val maps = binding?.mapButton

        greet?.text = "Welcome $loggedin"

        createEvent?.setOnClickListener {
            // Replace action with correct direction
            findNavController().navigate(R.id.action_loggedIn_to_createEvent)
        }

        browseEvent?.setOnClickListener {
            // Replace action with correct direction
            findNavController().navigate(R.id.action_loggedIn_to_postListFragment)
        }

        logOut?.setOnClickListener {
            // Replace action with correct directionhi@hot.com
            findNavController().navigate(R.id.action_loggedIn_to_logIn)
        }

        profile?.setOnClickListener {
            // Replace action with correct direction
            findNavController().navigate(LoggedInDirections
                .actionLoggedInToUserProfile(username))
        }

        maps?.setOnClickListener {
            // Replace action with correct direction
            findNavController().navigate(R.id.action_loggedIn_to_map)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}


