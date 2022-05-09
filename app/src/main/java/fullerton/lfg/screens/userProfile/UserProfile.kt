package fullerton.lfg.screens.userProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import fullerton.lfg.R
import fullerton.lfg.databinding.UserProfileBinding
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController


class UserProfile : Fragment() {

    private var binding: UserProfileBinding? = null
    val args: UserProfileArgs by navArgs()

    private val userProfileViewModel: UserProfileViewModel by activityViewModels()
    private lateinit var userProfileBinding: UserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        userProfileBinding = UserProfileBinding.inflate(inflater, container, false)
        binding = userProfileBinding

        return userProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding?.apply {
//            lifecycleOwner = viewLifecycleOwner
//            userProfileViewModel = userProfileViewModel
//        }
        val profile = userProfileViewModel._userProfile

//        val pfn = profile.value?.firstname
//        val pln = profile.value?.lastname

        val firstname = binding?.firstname
        val lastname = binding?.lastname

        firstname?.text = "Welcome"
        lastname?.text = "Goodbye"

    }

}