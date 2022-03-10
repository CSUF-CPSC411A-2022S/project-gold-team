package fullerton.lfg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fullerton.lfg.databinding.CreateEventBinding


class CreateEvent : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = CreateEventBinding.inflate(layoutInflater)
        return binding.root
    }
}