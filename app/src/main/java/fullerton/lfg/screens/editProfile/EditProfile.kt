package fullerton.lfg.screens.editProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fullerton.lfg.R
import fullerton.lfg.databinding.EditProfileBinding
import androidx.navigation.fragment.navArgs
import fullerton.lfg.database.ProfileDatabase


class EditProfile : Fragment() {

    private var binding: EditProfileBinding? = null
    private val args: EditProfileArgs by navArgs()

    private lateinit var editProfileViewModel: EditProfileViewModel
    private lateinit var editProfileBinding: EditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editProfileBinding = EditProfileBinding.inflate(inflater, container, false)
        binding = editProfileBinding
        return editProfileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val database = ProfileDatabase.getInstance(application).profileDao()

        val viewModelFactory = EditProfileViewModelFactory(database, application)
        editProfileViewModel = ViewModelProvider(
            this, viewModelFactory)[EditProfileViewModel::class.java]

        //field bindings
        val firstNameField = binding?.editProfileFirstName
        val lastNameField = binding?.editProfileLastName
        val passwordField = binding?.editProfilePassword
        val confirmPWField = binding?.editConfirmNewPassword

        //button bindings
        val clearBtn = binding?.editProfileClearBtn
        val confirmBtn = binding?.editProfileConfirmBtn
        val backBtn = binding?.editProfileBackBtn

        // vars retrieved from args
        val userEmail = args.email

        clearBtn?.setOnClickListener {
            firstNameField?.setText("")
            lastNameField?.setText("")
            passwordField?.setText("")
            confirmPWField?.setText("")
        }

        confirmBtn?.setOnClickListener {
            val currentFN = firstNameField?.text.toString()
            val currentLN = lastNameField?.text.toString()
            val currentPW = passwordField?.text.toString()
            val currentConfirmPW = confirmPWField?.text.toString()

            if (currentPW.length in 1..5) {
                Toast.makeText(activity,
                    "Passwords must be >5 characters", Toast.LENGTH_LONG).show()
            }
            else if (currentPW != currentConfirmPW) {
                Toast.makeText(activity,
                    "Passwords must match", Toast.LENGTH_LONG).show()
            }
            else {
                // if user filled in first name field, update it
                if (currentFN.isNotBlank()) {
                    editProfileViewModel.updateUserFirstName(userEmail, currentFN)
                }
                if (currentLN.isNotBlank()) {
                    editProfileViewModel.updateUserLastName(userEmail, currentLN)
                }
                if (currentPW != "") {
                    editProfileViewModel.updateUserPassword(userEmail, currentPW)
                }
                Toast.makeText(activity,
                    "Profile Updated Successfully", Toast.LENGTH_LONG).show()
            }
        }

        backBtn?.setOnClickListener {
            findNavController().navigate(
                EditProfileDirections.actionEditProfileToUserProfile2(userEmail))
        }
    }
}