package fullerton.lfg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import fullerton.lfg.databinding.EventInfoBinding
import fullerton.lfg.DataSource.*
import fullerton.lfg.BlogPost.*

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

        val tempTitle = "Congratualtions!"
        val tempBody = "You made it to the end of the course!\n" + "\n" + "Next we'll be building the REST API!"
        val tempImg = "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png"
        val tempUser = "Sally"

        val tempList = ArrayList<BlogPost>()

        binding.eventName.setText(BlogPost.title)
        binding.description.setText(BlogPost.body)
        binding.time.setText(BlogPost.image)
        binding.location.setText(BlogPost.username)

        binding.addEvent.setOnClickListener{
            tempList.add(BlogPost( tempTitle, tempBody, tempImg, tempUser))
            val toast = Toast.makeText(getContext(), "Event has been added.", Toast.LENGTH_SHORT).show()
        }
        println(tempList)
        return binding.root
    }
}