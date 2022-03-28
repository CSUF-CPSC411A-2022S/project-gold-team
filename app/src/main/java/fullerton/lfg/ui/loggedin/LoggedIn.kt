package fullerton.lfg.ui.loggedin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import fullerton.lfg.R
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}