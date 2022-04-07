package fullerton.lfg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
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
//        val list = ArrayList<BlogPost>() I would need to import this from Josh's code so we can push onto the array.
//        val list = ArrayList<String>()

        binding.addEvent.setOnClickListener{
//            list.add(EditText.text.toString())
            val toast = Toast.makeText(getContext(), "Event has been added.", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

}