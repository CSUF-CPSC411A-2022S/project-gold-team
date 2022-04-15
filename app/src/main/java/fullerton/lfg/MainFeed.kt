package fullerton.lfg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import fullerton.lfg.RecyclerView.BlogRecyclerAdapter
import fullerton.lfg.RecyclerView.DataSource
import fullerton.lfg.RecyclerView.TopSpacingItemDecoration
import fullerton.lfg.databinding.FragmentMainFeedBinding
import kotlinx.android.synthetic.main.fragment_main_feed.*


import androidx.appcompat.app.AppCompatActivity


import kotlinx.android.synthetic.recycler_view.*
import fullerton.lfg.models.BlogPost

import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream



class MainFeed : Fragment() {


    private lateinit var blogAdapter: BlogRecyclerAdapter

    private var binding: FragmentMainFeedBinding? = null
    private lateinit var MainFeedBinding: FragmentMainFeedBinding
    //private val mainFeedViewModel:


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //setContentView(R.layout.fragment_main_feed)
        // Inflate the layout for this fragment
        MainFeedBinding = FragmentMainFeedBinding.inflate(inflater, container, false)
        binding = MainFeedBinding
        return MainFeedBinding.root

        initRecyclerView()
        addDataSet()
    }

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        blogAdapter.submitList(data)
    }

    private fun initRecyclerView(){

        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            blogAdapter = BlogRecyclerAdapter()
            adapter = blogAdapter
        }
    }


}