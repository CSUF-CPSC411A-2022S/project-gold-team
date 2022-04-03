package fullerton.lfg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.recycler_view.*
import kotlinx.android.synthetic.main.activity_main.*


class MainFeed : AppCompatActivity()
{
    private lateinit var blogRecyclerAdapter: BlogRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_blog_list_item)
        initRecylcerView()
    }

    private fun addDataSet(){
        val data = DataSource.createDataSet()
        blogRecyclerAdapter.submitList(data)
    }


    private fun initRecylcerView(){
        recycler_view.apply{
           layoutManager = LinearLayoutManager(this@MainFeed)
            val topSpacingItemDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingItemDecoration)
            blogRecyclerAdapter = BlogRecyclerAdapter()
            adapter = blogRecyclerAdapter
        }
    }
}
