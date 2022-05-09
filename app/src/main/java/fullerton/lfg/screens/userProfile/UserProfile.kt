package fullerton.lfg.screens.userProfile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fullerton.lfg.R
import fullerton.lfg.databinding.UserProfileBinding
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController
import fullerton.lfg.database.Profile
import fullerton.lfg.database.ProfileDatabase


class UserProfile : Fragment() {

    private var binding: UserProfileBinding? = null
    val args: UserProfileArgs by navArgs()

    private lateinit var userProfileViewModel: UserProfileViewModel
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

        val application = requireNotNull(this.activity).application
        val database = ProfileDatabase.getInstance(application).profileDao()

        val fullname = binding?.fullname
        val viewModelFactory = UserProfileViewModelFactory(database, application, args.email)
        userProfileViewModel = ViewModelProvider(this,
            viewModelFactory)[UserProfileViewModel::class.java]
        val email = args.email
        Log.i("test email", email)

        userProfileViewModel.getUserProfile(email).observe(viewLifecycleOwner, Observer {
            userProfile ->
                if (userProfile != null) {
                    var fn = userProfile.firstname + " " + userProfile.lastname
                    fullname?.text = fn
                }
                else {
                    Log.w("tag2", "null userProfile")
                }
        })

    }

}