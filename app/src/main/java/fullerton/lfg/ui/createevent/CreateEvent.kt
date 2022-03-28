package fullerton.lfg.ui.createevent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import fullerton.lfg.databinding.CreateEventBinding


class CreateEvent : Fragment() {

    private var binding: CreateEventBinding? = null

    private val createEventViewModel: CreateEventViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val createEventBinding = CreateEventBinding.inflate(inflater, container, false)
        binding = createEventBinding

        return createEventBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            createEventviewModel = createEventViewModel
            createevent = this@CreateEvent
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}