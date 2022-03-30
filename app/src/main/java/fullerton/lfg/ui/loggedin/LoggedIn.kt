package fullerton.lfg.ui.loggedin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import fullerton.lfg.R
import fullerton.lfg.data.TestProfile
import fullerton.lfg.databinding.LoggedInBinding


class LoggedIn : Fragment() {

    private var binding: LoggedInBinding? = null

    private val loggedInViewModel: LoggedInViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val loggedInBinding = LoggedInBinding.inflate(inflater, container, false)
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
        val greet = binding?.greeting


        greet?.text = TestProfile.displayName
        loggedInViewModel.loggedInDisplayName.observe(viewLifecycleOwner, Observer {
            val loggedInDisplayName = it ?: return@Observer

            loggedInViewModel.loggedInUserView()
            greet?.text = loggedInDisplayName
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}