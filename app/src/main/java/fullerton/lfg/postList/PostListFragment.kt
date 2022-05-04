package fullerton.lfg.postList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import fullerton.lfg.PostDatabase.PostDatabase
import fullerton.lfg.databinding.FragmentMainFeedBinding
import fullerton.lfg.R

/**
 * Fragment that displays the input text fields and intersection list
 */
class IntersectionListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Create data binding
        val binding: FragmentMainFeedBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_main_feed, container, false)

        // Get reference to the application
        val application = requireNotNull(this.activity).application

        // Retrieve Intersection data access object.
        val dataSource = PostDatabase.getInstance(application).postDao

        // Create a factory that generates IntersectionViewModels connected to the database.
        val viewModelFactory = PostViewModelFactory(dataSource, application)

        // Generate an IntersectionViewModel using the factory.
        val intersectionViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(PostViewModel::class.java)

        // Connect the IntersectionViewModel with the variable in the layout
        binding.postViewModel = intersectionViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        var postAdapter = PostListAdapter()

        // Attach intersection adapter.
        binding.intersectionRecyclerview.adapter = postAdapter

        // Submit an updated list to the intersection listAdapter whenever the data changes. Take note
        // intersectionList is a LiveData object.
        intersectionViewModel.intersectionList.observe(viewLifecycleOwner, Observer {
            it?.let {
                postAdapter.submitList(it)
            }
        })
        return binding.root
    }
}