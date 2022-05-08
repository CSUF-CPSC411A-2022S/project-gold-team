package fullerton.lfg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fullerton.lfg.databinding.FragmentCreateEventBinding
import fullerton.lfg.databinding.FragmentPostListBinding
import fullerton.lfg.postDatabase.PostDatabase

import fullerton.lfg.postList.PostListAdapter
import fullerton.lfg.postList.PostViewModel
import fullerton.lfg.postList.PostViewModelFactory


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
        // Create data binding
        val binding: FragmentCreateEventBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_create_event, container, false)

        // Get reference to the application
        val application = requireNotNull(this.activity).application

        // Retrieve Intersection data access object.
        val dataSource = PostDatabase.getInstance(application).postDao

        // Create a factory that generates IntersectionViewModels connected to the database.
        val viewModelFactory = PostViewModelFactory(dataSource, application)

        // Generate an IntersectionViewModel using the factory.
        val postViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(PostViewModel::class.java)

        // Connect the IntersectionViewModel with the variable in the layout
        binding.postViewModel = postViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        var postAdapter = PostListAdapter()

        // Attach intersection adapter.
//        binding.postRecyclerview.adapter = postAdapter

        // Submit an updated list to the intersection listAdapter whenever the data changes. Take note
        // intersectionList is a LiveData object.
        postViewModel.postList.observe(viewLifecycleOwner, Observer {
            it?.let {
                postAdapter.submitList(it)
            }
        })
        return binding.root
    }
}