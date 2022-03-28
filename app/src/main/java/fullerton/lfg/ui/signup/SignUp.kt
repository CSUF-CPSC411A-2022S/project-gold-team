package fullerton.lfg.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import fullerton.lfg.databinding.SignUpBinding


class SignUp : Fragment() {

    private var binding: SignUpBinding? = null

    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val signUpBinding = SignUpBinding.inflate(inflater, container, false)
        binding = signUpBinding
        return signUpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            signUpviewModel = signUpViewModel

            // Assign the fragment
            signUp = this@SignUp
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}