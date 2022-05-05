package fullerton.lfg.postList


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import fullerton.lfg.postDatabase.Post

import fullerton.lfg.databinding.LayoutBlogListItemBinding


/**
 * A RecyclerView adapter that uses the DiffCallback for better performance.
 */
class PostListAdapter() : ListAdapter<Post,
        PostListAdapter.ItemViewHolder>(PostDiffCallback()) {

    /**
     * Inner class used to store data about each element in the RecyclerView. We provide a binding
     * to make it easy to access elements of the layout.
     */
    class ItemViewHolder(val binding: LayoutBlogListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Assign an intersection object
         */
        fun bind(item: Post) {
            binding.post = item
        }
    }

    /**
     * Creates a View to visualize one element in the RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // We use an inflater based on the parent (IntersectionListFragment) and create an
        // ItemViewHolder with binding to the layout to simplify access.
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutBlogListItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    /**
     * The function is called whenever the RecyclerView displays a specific element. It provides
     * access to the ItemViewHolder and its position.
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // Assign the corresponding element from the data and the click listener. Take note getItem
        // is a function provided by ListAdapter.
        holder.bind(getItem(position))
    }
}

/**
 * Manages a RecyclerView list using the Eugene W. Myers's difference algorithm to reduce processing.
 */
class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    /**
     * We use intersectionId because it is a unique ID referring to a single element.
     */
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.postId == newItem.postId
    }

    /**
     * We check all properties to check equality between Intersection objects.
     */
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.location == newItem.location &&
                oldItem.body == newItem.body &&
                oldItem.title == newItem.title

    }
}


/**
 * Listener that accepts a lambda function clickListener. It will be called when an element of the
 * RecyclerView is clicked/tapped.
 */
class PostListener(val clickListener: (postId: Long) -> Unit) {
    fun onClick(post: Post) = clickListener(post.postId)
}