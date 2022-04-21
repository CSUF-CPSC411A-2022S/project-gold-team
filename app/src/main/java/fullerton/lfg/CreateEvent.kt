package fullerton.lfg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView

/**
 * A simple [Fragment] subclass.
 * Use the [CreateEvent.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateEvent : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.create_event, container, false)
    }
}