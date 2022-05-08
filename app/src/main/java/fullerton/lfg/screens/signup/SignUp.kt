package fullerton.lfg.screens.signup

import android.os.Bundle
import android.text.Editable

import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo

import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fullerton.lfg.R
import fullerton.lfg.data.model.User
import fullerton.lfg.database.ProfileDatabase
import fullerton.lfg.databinding.SignUpBinding


class SignUp : Fragment() {

    private var binding: SignUpBinding? = null

    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var signUpBinding: SignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        signUpBinding = SignUpBinding.inflate(inflater, container, false)
        binding = signUpBinding
        return signUpBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = ProfileDatabase.getInstance(application).profileDao

        val viewModelFactory = SignUpViewModelFactory(dataSource, application)

        signUpViewModel = ViewModelProvider(this, viewModelFactory)[SignUpViewModel::class.java]

        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            signUpviewModel = signUpViewModel

            // Assign the fragment
            signUp = this@SignUp
        }


        val firstName = binding?.firstName
        val lastName = binding?.lastName
        val email = binding?.createEmail
        val password = binding?.createPassword
        val confirmPassword = binding?.confirmPassword
        val submit = binding?.submitButton
        val cancel = binding?.cancelButton
        val loading = binding?.loading



        signUpViewModel.signupFormState.observe(viewLifecycleOwner, Observer {
            val signupState = it ?: return@Observer

            // disable submit button unless all is valid
            submit?.isEnabled = signupState.isDataValid

            if (signupState.firstNameError != null) {
                firstName?.error = getString(signupState.firstNameError)
            }
            if (signupState.lastNameError != null) {
                lastName?.error = getString(signupState.lastNameError)
            }
            if (signupState.usernameError != null) {
                email?.error = getString(signupState.usernameError)
            }
            if (signupState.passwordError != null) {
                password?.error = getString(signupState.passwordError)
            }
            if (signupState.confirmPasswordError != null) {
                confirmPassword?.error = getString(signupState.confirmPasswordError)
            }
        })

        signUpViewModel.allProfiles.observe(viewLifecycleOwner){
            profiles ->

        }

        signUpViewModel.getProfile?.observe(viewLifecycleOwner, Observer {
            val profile = it ?: return@Observer
        })


        signUpViewModel.signupResult.observe(viewLifecycleOwner, Observer {
            val signupResult = it ?: return@Observer

            loading?.visibility = View.GONE

            if (signupResult.error != null) {
                showSignupFailed(signupResult.error)
                Log.i("Testing", "Inside SignupResult.error")
            }
            if (signupResult.success != null) {

                signUpViewModel.insert(
                    firstname = signupResult.success.firstName,
                    lastname = signupResult.success.lastName,
                    username = signupResult.success.userId,
                    password = signupResult.success.password
                )

                Log.i("Testing", "Inside SignupResult.success")
                updateUiWithUser(signupResult.success)
            }

        })




        firstName?.afterTextChanged {
            signUpViewModel.signupDataChanged(
                firstName.text.toString(),
                lastName?.text.toString(),
                email?.text.toString(),
                password?.text.toString(),
                confirmPassword?.text.toString()
            )
        }

        lastName?.afterTextChanged {
            signUpViewModel.signupDataChanged(
                firstName?.text.toString(),
                lastName.text.toString(),
                email?.text.toString(),
                password?.text.toString(),
                confirmPassword?.text.toString()
            )
        }

        email?.afterTextChanged {
            signUpViewModel.signupDataChanged(
                firstName?.text.toString(),
                lastName?.text.toString(),
                email.text.toString(),
                password?.text.toString(),
                confirmPassword?.text.toString()
            )
        }

        password?.apply {
            afterTextChanged {
                signUpViewModel.signupDataChanged(
                    firstName?.text.toString(),
                    lastName?.text.toString(),
                    email?.text.toString(),
                    password.text.toString(),
                    confirmPassword?.text.toString()

                )
            }
        }

        confirmPassword?.apply {
            afterTextChanged {
                signUpViewModel.signupDataChanged(
                    firstName?.text.toString(),
                    lastName?.text.toString(),
                    email?.text.toString(),
                    password?.text.toString(),
                    confirmPassword.text.toString()

                )
            }
        }

        signUpBinding.signupTextView.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE ->
                    signUpViewModel.signUpUser(
                        firstName?.text.toString(),
                        lastName?.text.toString(),
                        email?.text.toString(),
                        password?.text.toString()
                    )
            }
            false
        }



        submit?.setOnClickListener {
            Log.i("Testing", "Inside Submit Button")
            loading?.visibility = View.VISIBLE

            signUpViewModel.signUpUser(
                firstName?.text.toString(),
                lastName?.text.toString(),
                email?.text.toString(),
                password?.text.toString()
            )

            Log.i("Testing", "Leaving Submit Button")

        }

        cancel?.setOnClickListener {
            findNavController().navigate(R.id.action_signUp_to_logIn)
        }

    }

    private fun updateUiWithUser(model: User?) {
        val welcome = getString(R.string.welcome)
        val displayName = model?.firstName.toString()
        //Toast.makeText(
            //requireContext(),
            //"$welcome $displayName",
            //Toast.LENGTH_LONG
        //).show()
        val action = SignUpDirections.actionSignUpToLoggedIn(displayName)
        findNavController().navigate(action)
        onDestroyView()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun showSignupFailed(@StringRes errorString: Int) {
        Toast.makeText(activity, errorString, Toast.LENGTH_SHORT).show()
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}