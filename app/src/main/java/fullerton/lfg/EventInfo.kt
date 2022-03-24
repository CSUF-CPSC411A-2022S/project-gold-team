package fullerton.lfg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import fullerton.lfg.databinding.EventInfoBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EventInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventInfo : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = EventInfoBinding.inflate(layoutInflater)

        binding.addEvent.setOnClickListener{
            val toast = Toast.makeText(getContext(), "Event has been added.", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}